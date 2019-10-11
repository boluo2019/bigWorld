

本概述是从《JDBCTM Database Access from JavaTM: A Tutorial and Annotated Reference》这本书中摘引来的。JavaSoft 目前正在准备这本书。这本书是一本教程，同时也是 JDBC 的重要参考手册，它将作为 Java 系列的组成部份，在 1997 年春季由 Addison-Wesley 出版公司出版。

8.1 概述
由于 SQL 数据类型和 Java 数据类型是不同的，因此需要某种机制在使用 Java 类型的应用程序和使用 SQL 类型的数据库之间来读写数据。

为此，JDBC 提供了 getXXX 和 setXXX 方法集、方法 registerOutParameter 和类 Types。

本章汇集了影响各种类和接口的数据类型的有关信息，并列出所有的对应关系表（这些表显示了 SQL 类型和 Java 类型之间的映射关系）以便于参考。

8.2 将 SQL 数据类型映射为 Java 类型
不幸的是，不同数据库产品所支持的 SQL 类型之间有很大的不同。即使不同的数据库以相同的语义支持 SQL 类型，它们也可能用不同的名称。例如，绝大多数的主流数据库都支持一种表示大型二进制值的 SQL 类型，但 Oracle 把这种类型叫做 LONG RAW，Sybase 把它叫做 IMAGE，Informix 却把它叫做 BYTE，而 DB2 又把它叫做 LONG VARCHAR FOR BIT DATA。

幸运的是，JDBC 程序员通常并不需要自己去关心目标数据库所用的实际 SQL 类型的名称。大多数时候，JDBC 程序员将根据一些现有的数据库表来进行编程。他们无须关心用于创建这些表的确切 SQL 类型的名称。

JDBC 在 java.sql.Types 类中定义了一系列的常规 SQL 类型标识符。这些类型可用于表示那些最为常用的 SQL 类型。在用 JDBC API 编程时，程序员通常可以使用这些 JDBC 类型来引用一般的 SQL 类型，而无须关心目标数据库所用的确切 SQL 类型的名称。在下一节中将对这些 JDBC 类型进行仔细说明。

程序员用到 SQL 类型名称的主要地方是在用 SQL 的 CREATE TABLE 语句创建新的数据库表时。这种情况下，程序员必须注意应该使用目标数据库所支持的 SQL 类型名称。如果需要知道各种 SQL 类型在某个特定的数据库中的行为的确切定义，我们建议查阅一下数据库文档。

如果想要编写一种可在各种数据库上创建表的可移植 JDBC 程序，用户主要有两个选择。第一个选择是：限制自己只使用那些被广为接受的 SQL 类型名称（例如 INTEGER、NUMERIC 或VARCHAR）。这些类型有可能能适应所有的数据库。第二个选择是：用 java.sql.DatabaseMetaData.getTypeInfo 方法来找出给定的数据库实际上支持哪些 SQL 类型，然后选择与给定 JDBC 类型相匹配的特定于数据库的 SQL 类型名。

JDBC 定义了一个从 JDBC 数据库类型到 Java 类型的标准映射。例如，JDBC 的 INTEGER 类型通常映射为 Java 的 int 类型。这可支持简单的接口，将 JDBC 值读写为简单的 Java 类型。

Java 类型不必与 JDBC 类型完全形同；它们只须能够用足够的类型信息来代表 JDBC 类型，从而能正确地存储和取出参数和从 SQL 语句恢复结果就可以了。例如，Java String 对象可能并不能精确地与任何 JDBC CHAR 类型匹配，但它却可给出足够的类型信息来成功地表示 CHAR、 VARCHAR 或 LONGVARCHAR 类型。

8.3 JDBC 类型
本节描述各种 JDBC 数据类型及其与标准 SQL 类型和 Java 类型的关联方式。

8.3.1 CHAR、 VARCHAR 和 LONGVARCHAR
JDBC 类型 CHAR、VARCHAR 和 LONGVARCHAR 密切相关。CHAR 表示固定长度的小字符串，VARCHAR 表示长度可变的小字符串，而 LONGVARCHAR 表示长度可变的大字符串。

与 JDBC CHAR 对应的是 SQL CHAR 类型，其定义由 SQL-92 给出，且所有主要的数据库都支持它。它接受用于指定字符串最大长度的参数，例如 CHAR(12) 即定义了一个长度为 12 个字符的字符串。所有主要的数据库都支持长度达 254 个字符的 CHAR。

与 JDBC VARCHAR 对应的是 SQL VARCHAR 类型，其定义由 SQL-92 给出，且所有的主要数据库都支持它。它接受用于指定字符串最大长度的参数，例如 VARCHAR(12) 即定义了一个最大长度为 12 个字符的字符串。所有主要数据库都至少支持长度达 254 个字符的 VARCHAR。当把字符串的值赋给 VARCHAR 变量时，数据库就记住该字符串的长度，使用 SELECT 时，它可以返回准确的原始字符串。

不幸的是，对于 JDBC LONGVARCHAR 类型，目前并没有一致的 SQL 映射。所有主要数据库都支持某种类型的长度可变的大字符串，这种字符串支持高达十亿位字节的数据，但 SQL 类型名称却变化多样。

Java 程序员不必区分 CHAR、VARCHAR 和 LONGVARCHAR 这三种类型的 JDBC 字符串。它们都可表示为 Java String，并且在不知道所需要的确切数据类型时也可正确读写 SQL 语句。

CHAR、VARCHAR 和 LONGVARCHAR 可映射为 String 或 char[]，但 String 更适合于一般用法。同时， String 类能使 String 和 char[] 之间的转换更为容易：它有一个用于将 String对象转换为 char[] 的方法，还有一个将 char[] 转换为 String 对象的构造函数。

必须提及的一个问题是：如何处理类型为 CHAR(n) 的固定长度的 SQL 字符串。答案是 JDBC 驱动程序（或 DBMS）将用适当的空格来进行填补。因此，当从数据库中检索 CHAR(n) 域时，驱动程序将把它转换为长度为 n 的 Java String 对象，对象末尾可能含有一些填补空格。反之，当把 String 对象送到某个 CHAR(n) 域时，驱动程序和/或数据库将在字符串的末尾填上一些必要的空格，使字符串的长度达到 n。

方法 ResultSet.getString 用于分配和返回新的 String 对象。我们建议用它来从 CHAR、VARCHAR 和LONGVARCHAR 域中检索数据。它适用于检索普通的数据，但如果用 JDBC 类型LONGVARCHAR 来储存多个兆字节的字符串时，用它进行检索将显得十分笨拙。为此，ResultSet 接口中有两个方法可供程序员将 LONGVARCHAR 值作为 Java 输入流进行检索，之后可从该流中以任意大小的块来读取数据。这两个方法是：getAsciiStream 和 getUnicodeStream，它们将把储存在 LONGVARCHAR 列的数据作为 Ascii 或 Unicode 字符流来传送。

8.3.2 BINARY、VARBINARY 和 LONGVARBINARY
JDBC 类型 BINARY、VARBINARY 和 LONGVARBINARY 密切相关。BINARY 表示固定长度的小二进制值， VARBINARY 表示长度可变化的小二进制值，而 LONGVARBINARY 表示长度可变化的大二进制值。

不幸的是，这些不同 BINARY 类型的使用还未被标准化，因而在各种主要数据库提供的支持有很大的不同。

对应于 JDBC BINARY 类型的 SQL BINARY 类型，是一种非标准的 SQL 扩展，只在某些数据库上才实现。它接受用于指定二进制字节数的参数。例如，BINARY(12) 即定义了一个长度为 12 个字节的 binary 类型。通常，BINARY 值被限定在 254 个字节以内。

对应于 JDBC VARBINARY 类型的 SQL VARBINARY 类型，是一种非标准的 SQL 扩展，只在某些数据库上才实现。它接受用于指定二进制字节最大数的参数。例如，VARBINARY(12) 即定义了一个长度最大可为 12 个字节的二进制类型。通常，VARBINARY 的值被限定在 254 个字节以内。当把二进制的值赋给 VARBINARY 变量时，数据库就记住这个所赋值的长度，调用 SELECT时，它返回准确的原始值。

遗憾的是，目前还没有一致的 SQL 类型名称与 JDBC LONGVARBINARY 类型相对应。所有主要数据库都支持某种类型的长度可变的大二进制类型，它可支持高达十亿个字节的数据，但 SQL 类型名称却变化多样。

在 Java 中，BINARY、VARBINARY 和 LONGVARBINARY 都可用同一 byte数组来表示。由于可在不知道所需的确切 BINARY 数据类型的情况下正确地读写 SQL 语句，因此，Java 程序员无需区分它们。

检索 BINARY 和 VARBINARY 值时，我们建议使用 ResultSet.getBytes。然而，如果类型为 JDBC LONGVARBINARY 的某列储存的是几兆字节长度的字节数组，则建议用方法getBinaryStream 来检索。与 LONGVARCHAR 的情形类似，该方法可以使 Java 程序员将 LONGVARBINARY 值作为 Java 输入流检索，然后可从该流中以更小的块来读取。

8.3.3 BIT
JDBC 类型 BIT 代表一个位值，可为 0 或 1。SQL-92 定义了 SQL BIT 类型。但与 JDBC BIT 类型不同，这种 SQL-92 BIT 类型带参数，用于定义固定长度的二进制字符串。幸运的是，SQL-92 也允许用简单的非参数化的 BIT 类型来代表单个的二进制数字。这种用法对应于 JDBC BIT 类型。不幸的是，SQL-92 BIT 类型只有在 “完全” SQL-92 中才要求，且目前只有一部份主流数据库支持它。因此，可移植的代码也许宁愿用 JDBC SMALLINT 类型，这种类型已得到广泛支持。

JDBC BIT 类型的 Java 映射的推荐类型是 Java 布尔型。

8.3.4 TINYINT
JDBC 类型 TINYINT 代表一个 8 位无符号整数，其值在 0 到 255 之间。

对应的 SQL 类型 TINYINT 目前只有一部份的数据库支持它。因此，可移植的代码也许宁愿用 JDBC SMALLINT 类型，这种类型已得到广泛支持。

JDBC TINYINT 类型的 Java 映射的推荐类型是 Java byte 或 Java short。8 位的 Java byte 类型代表一个有符号的整数，其值在 -128 到 127 之间，因此对于大的 TINYINT 值它并非总合适，而 16 位的 Java short 类型却总能存储所有的 TINYINT 值。

8.3.5 SMALLINT
JDBC 类型 SMALLINT 代表一个 16 位的有符号整数，其值在 -32768 和 32767 之间。

对应的 SQL 类型 SMALLINT，其定义由 SQL- 92 给出，并为所有主流数据库所支持。SQL-92 标准将 SMALLINT 的精度留给实现去决定。但事实上，所有的主流数据库都至少支持 16 位。

JDBC SMALLINT 类型的 Java 映射的推荐类型是 Java short 类型。

8.3.6 INTEGER
JDBC 类型 INTEGER 代表一个 32 位的有符号整数，其值在 - 2147483648 和 2147483647 之间。

对应的 SQL 类型 INTEGER，其定义由 SQL- 92 给出，并为所有主流数据库所广为支持。SQL-92 标准将 INTEGER 的精度留给实现去决定。但事实上，所有的主流数据库都至少支持 32 位。

INTEGER 类型 Java 映射的推荐类型是 Java int 类型。

8.3.7 BIGINT
JDBC 类型 BIGINT 代表一个 64 位的有符号整数，其值在 -9223372036854775808 和 9223372036854775807 之间。

对应的 SQL 类型 BIGINT 是 SQL 的一个非标准扩展。事实上，目前还没有任何数据库实现 SQL BIGINT 类型。我们建议在可移植的代码中避免使用该类型。

BIGINT 类型的 Java 映射的推荐类型是 Java long 类型。

8.3.8 REAL
JDBC 类型 REAL 代表一个有 7 位尾数的“单精度”浮点数。

对应的 SQL 类型 REAL，其定义由 SQL- 92 给出。虽然未得到普遍支持，但在主流数据库中却已得到广泛支持。SQL-92 标准将 REAL 的精度留给实现去决定。但事实上，所有的支持 REAL类型的主流数据库都支持至少 7 位数的尾数精度。

REAL 类型的 Java 映射的推荐类型为 Java float 类型。

8.3.9 DOUBLE
JDBC 类型 DOUBLE 代表一个有 15 位尾数的“双精度”浮点数。

对应的 SQL 类型是 DOUBLE PRECISION，其定义由 SQL- 92 给出，并为主流数据库所广为支持。SQL-92 标准将 DOUBLE PRECISION 的精度留给实现去决定。但事实上，所有支持 DOUBLEPRECISION 类型的主流数据库都支持至少 15 位数的尾数精度。

DOUBLE 类型的 Java 映射的推荐类型为 Java double 类型。

8.3.10 FLOAT
JDBC 类型 FLOAT 基本上与 JDBC 类型 DOUBLE 相同。我们同时提供了 FLOAT 和 DOUBLE，其目的是与以前的 API 实现一致。但这却有可能产生误导。FLOAT 代表一个有 15 位尾数的“双精度”浮点数。

对应的 SQL 类型 FLOAT，其定义由 SQL-92 给出。SQL-92 标准将 FLOAT 的精度留给实现去决定。但事实上，所有支持 FLOAT 类型的主流数据库都支持至少 15 位数的尾数精度。

FLOAT 类型的 Java 映射的推荐类型为 Java double 类型。然而，由于 SQL FLOAT 和单精度的 Java float类型间可能产生混淆，因此建议 JDBC 程序员通常选用 JDBC DOUBLE 类型而不选用 FLOAT。

8.3.11 DECIMAL 和 NUMERIC
JDBC 类型 DECIMAL 和 NUMERIC 两者非常相似。它们都表示固定精度的十进制值。

相应的 SQL 类型 DECIMAL 和 NUMERIC，其定义在 SQL-92 中给出，并得到广泛支持。这些 SQL 类型都带有精度和比例参数。精度是所支持的十进制数字的总位数，比例是小数点后的数字位数。比例必须永远小于或等于精度。例如，值 "12.345" 有 5 位精度和 3 位比例，而值 ".11" 有 2 位精度和 2 位比例。JDBC 要求所有 DECIMAL 和 NUMERIC 类型都必须支持至少 15 位的精度和比例。

DECIMAL 和 NUMERIC 之间的唯一区别是 SQL-92 规范要求 NUMERIC 类型必须以确切指定的精度来表示，而对 DECIMAL 类型，它允许实现在创建该类型时所指定的精度以外再添加额外的精度。因此，创建为类型 NUMERIC(12,4) 的列将总是用 12 位数来表示，而创建为类型 DECIMAL(12,4) 的列则可用更大的位数来表示。

DECIMAL 和 NUMERIC 类型的 Java 映射的推荐类型是 java.math.BigDecimal，该 Java 类型也用绝对精度来表示定点数。java.math.BigDecimal 类型提供了一些数学操作，可对BigDecimal 类型与其它的 BigDecimal 类型、整数类型和浮点数类型进行加、减、乘、除的运算。

用于检索 DECIMAL 和 NUMERIC 值的推荐方法是 ResultSet.getBigDecimal。JDBC 还允许将这些 SQL 类型作为简单的 Strings 或 char 数组来访问。因此，Java 程序员可用getString 来检索 DECIMAL 或 NUMERIC 结果。然而，这将使常见的用 DECIMAL 或 NUMERIC 来表示的货币值变得极为尴尬，因为它意味着应用程序编程人员必须对字符串进行数学运算。当然，也可将这些 SQL 类型作为 Java 数值型类型来检索。

8.3.12 DATE、TIME 和 TIMESTAMP
有三种 JDBC 类型与时间有关：

JDBC DATE 类型表示一个由年、月、日组成的日期。对应的是 SQL DATE 类型，其定义由 SQL-92 给出，但只有一部份主流数据库实现它。某些数据库提供了另外一些支持类似语义的 SQL 类型。
JDBC TIME 类型表示一个由小时、分钟和秒组成的时间。对应的是 SQL TIME 类型，其定义由 SQL-92 给出，但只有一部份主流数据库实现它。与 DATE 一样，某些数据库提供了另外一些支持类似语义的 SQL 类型。
JDBC TIMESTAMP 类型表示 DATE 加上 TIME，外加一个纳秒域。对应的 TIMESTAMP 类型，其定义由 SQL-92 给出，但只有少数几个数据库实现它。
由于标准的 Java 类 java.util.Date 并不与这三个 JDBC 日期—时间类型完全匹配（它含有 DATE 和 TIME 的信息但不含纳秒信息），因此 JDBC 定义了三个 java.util.Date 的子类与 SQL 类型对应。它们是：

java.sql.Date，对应于 SQL DATE 信息。java.util.Date 基本类中的小时、分钟和秒都设为 0。
java.sql.Time，对应于 SQL TIME 信息。java.util.Date 基本类中的年、月、日域设为 1970 年 1 月 1 日。这是 Java 纪元的“零”日期。
java.sql.Timestamp，对应于 SQL TIMESTAMP 信息。该类扩展了 java.util.Date，添加了纳秒域。
所有这三个与时间有关的 JDBC 类都是 java.util.Date 的子类，因此它们可用在任何可以使用 java.util.Date 的地方。例如，国际化 (internationalization) 方法将 java.util.Date 对象用作变量，因此可将这三个与时间有关的 JDBC 类中任何一个的实例作为参数传给国际化方法。

JDBC Timestamp 对象除了具有其父类的日期和时间成份外，还有一个独立的纳秒组件。如果将 java.sql.Timestamp 对象用于需要 java.util.Date 对象的地方，则纳秒组件将丢失。但由于是以毫秒的精度来储存 java.util.Date 对象的，因此将 java.sql.Timestamp 对象转换为 java.util.Date 对象时可以保持这样的精度。这可通过将纳秒组件中的纳秒转换为毫秒（用纳秒数除以 1,000,000）并将之添到 java.util.Date 对象中来实现。转换中可能丢失高达 999,999 纳秒，但所产生的 java.util.Date 对象将可精确到毫秒以内。

下述代码段将 java.sql.Timestamp 对象转换为精度达到毫秒量级的 java.util.Date 对象：

    Timestamp t = new Timestamp(100, 0, 1, 15, 45, 29, 987245732);
    java.util.Date d;
    d = new java.util.Date(t.getTime() + (t.getNanos() / 1000000));
8.4 映射示例
任何情况下，当 Java 程序要从数据库中检索数据时，必须存在某种形式的映射和数据转换。大多数时候， JDBC 程序员将在知道其目标数据库机制的情况下进行编程。例如，他们将知道数据库含有哪些表、表中每一列的数据类型。因此，他们可使用 ResultSet、 PreparedStatement和 CallableStatement 接口中那些与类型有关的存取方法。本节给出三个示例，描述各种情形中所要求的数据映射和转换。

8.4.1 简单的 SQL 语句
在最常见的情形中，用户将执行简单的 SQL 语句，然后取回含有结果的 ResultSet 对象。由数据库返回并存放在 ResultSet 列的值，其类型为 JDBC 数据类型。调用ResultSet.getXXX 方法将把该值检索为 Java 数据类型。例如，如果某个 ResultSet 列含有一个 JDBC FLOAT 值，则方法 getDouble 将把它检索为 Java double 类型。8.6.6 节所示的表显示了哪些 getXXX 方法可检索哪些 JDBC 类型（如果用户不知道某个 ResultSet 列的类型，可通过调用 ResultSet.getMetaData 方法来获得有关信息，然后再调用ResultSetMetaData 的 getColumnType 或 getColumnTypeName 方法）。以下代码段示范了如何获得结果中各列的类型名称：

    String query = "select * from Table1";
    ResultSet rs = stmt.executeQuery(query);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    for (int i = 1; i <= columnCount; i++)  {
      String s = rsmd.getColumnTypeName(i);
      System.out.println ("Column " + i + " is type " + s);
    }
8.4.2 带 IN 参数的 SQL 语句
在另一个可能的情况中，用户将发送带输入参数的 SQL 语句。这种情况下，用户通过调用 PreparedStatement.setXXX 方法为每个输入参数赋值。例如，PreparedStatement.setLong(1, 2345678) 将把值 2345678 作为 Java 的 long 类型赋给第一个参数。为了将 2345678 到数据库中，驱动程序将把它转换为 JDBC BIGINT。驱动程序将把哪种 JDBC 类型送到数据库中是由 Java 类型到 JDBC 类型的标准映射所决定的

8.4.3 带 OUT 参数的 SQL 语句
还有一个情况是，用户要调用已存储过程，将值赋给其 INOUT 参数，从结果中检索值，然后从参数中检索值。这种情形极为少见且相当复杂，但它却不失为映射和数据转换的好范例。

这种情况下，首先要做的是用 PreparedStatement.setXXX 方法对 INOUT 参数赋值。此外，由于这些参数同时也用于输出，因此程序员必须为每个参数注册 JDBC 类型，该类型是数据库所要返回给该参数的值的 JDBC 类型。这可用 CallableStatement.registerOutParameter 方法来完成，后者接受在类 Types 中所定义的 JDBC 类型作为其变量。程序员可以用ResultSet.getXXX 方法系列来检索返回给ResultSet 对象的结果，用 CallableStatement.getXXX 方法系列来检索存放在输出参数中的值。

用于 ResultSet.getXXX 方法的 XXX 类型在某些情况下非常灵活。8.6.6 节中所示的表显示了哪些 ResultSet.getXXX 方法可用于检索哪些 JDBC 类型。

用于 CallableStatement.getXXX 方法的 XXX 类型必须映射为那个参数所注册的 JDBC 类型。例如，如果数据库应返回类型为 JDBC REAL 的输出值，则该参数应被注册为java.sql.Types.REAL。因此，要检索该 JDBC REAL 值，必须调用 CallableStatement.getFloat 方法（从 JDBC 类型到 Java 类型的映射在 8.6.1 节中的表中给出）。方法 getFloat先把储存在输出参数中的值从 JDBC REAL 类型转换为 Java float 类型，然后将它返回。为了适应各种数据库和使应用程序具有更高的可移植性，建议先检索 ResultSet 对象中的值，再检索输出参数中的值。

下述代码示范的是调用名为 getTestData 的已存储过程。它有两个参数，且都是 INOUT 参数。首先， Connection 对象 con 将创建 CallableStatement 对象 cstmt。然后，方法setByte 把第一个参数设置为 Java byte 类型，其值为 25。驱动程序将把 25 转换为 JDBC TINYINT 类型并将之送到数据库中。方法 setBigDecimal 用输入值 83.75 来设置第二个参数。驱动程序将把这个 java.math.BigDecimal 对象转换为 JDBC NUMERIC 值。接下来将这两个参数注册为 OUT 参数，第一个参数注册为 JDBC TINYINT 类型，第二个参数注册为小数点后面带两位数字的 JDBC DECIMAL 类型。执行 cstmt 后，就用 ResultSet.getXXX 方法将值从 ResultSet 对象中检索出来。方法 getString 将第一列中的值作为 Java String 对象获取，getInt 将第二列中的值作为 Java int 获取，getInt 将第三列中的值作为 Java int 获取。

之后， CallableStatement.getXXX 方法检索存放在输出参数中的值。方法 getByte 将 JDBC TINYINT 检索为 Java byte，getBigDecimal 将 JDBC DECIMAL 检索为小数点后面带有两位数字的 java.math.BigDecimal 对象。注意，当参数既是输入参数同时又是输出参数时，setXXX 方法所用的 Java 类型与 getXXX 方法所用的相同（正如 setByte 和 getByte 中一样）。registerOutParameter 方法将它注册成由 Java 类型映射来的 JDBC 类型（Java byte 类型映射为 JDBC TINYINT，如 8.6.2 节中的表所示）。

    CallableStatement cstmt = con.prepareCall(
          "{call getTestData(?, ?)}");
    cstmt.setByte(1, 25);
    cstmt.setBigDecimal(2, 83.75);
    // 将第一个参数注册为 JDBC TINYINT，第二个
    // 参数注册为小数点后面带有两位数字的 JDBC DECIMAL 类型
    cstmt.registerOutParameter(1, java.sql.Types.TINYINT);
    cstmt.registerOutParameter(2, java.sql.Types.DECIMAL, 2);
    ResultSet rs = cstmt.executeUpdate();
    // 检索并打印结果中的值。
    while(rs.next()) {
      String name = rs.getString(1);
      int score = rs.getInt(2);
      int percentile = rs.getInt(3);
      System.out.print("name = " + name + ", score = " + score + ", "
      System.out.println("percentile = " + percentile);
    // 检索输出参数中的值
    byte x = cstmt.getByte(1);
    java.math.BigDecimal n = cstmt.getBigDecimal(2, 2);
总之，CallableStatement.getXXX 和 PreparedStatement.setXXX 方法系列中的 XXX 是 Java 类型。对于 setXXX 方法，驱动程序先把 Java 类型转换为 JDBC 类型，再把它送到数据库中（使用 8.6.2 节中的表所示的标准映射）。对于 getXXX 方法， 驱动程序先把数据库返回的 JDBC 类型转换为 Java 类型（用 8.6.1 节表中所示的标准映射），再把它返回给 getXXX 方法。

registerOutParameter 方法只接受 JDBC 类型的变量，而 setObject 方法却可接受 JDBC 类型的变量。

注意，如果在可选的第三个变量的位置上提供了 JDBC 类型，则 setObject 方法将把参数值从 Java 类型显式地转换为所指定的 JDBC 类型。如果没有为 setObject 提供目标 JDBC 类型，则将把参数值转换为 Java 类型的标准映射 JDBC 类型（如 8.6.2 节的表中所示）。在将参数送到数据库中之前，驱动程序都要进行显式或隐式转换。

8.5 动态数据存取
大多数时候，用户要存取的结果和参数其数据类型在编译时是已知的。然而，有些应用程序（例如普通的浏览器或查询工具）在编译时对它们所要存取的数据库的机制并不知晓。因此，JDBC 除了支持静态数据类型存取外，还支持类型完全动态确定的数据存取。

有三种方法和一个常量可用于访问那些在编译时其数据类型尚属未知的值：

ResultSet.getObject
PreparedStatement.setObject
CallableStatement.getObject
java.sql.Types.OTHER （用作 CallableStatement.registerOutParameter 的一个变量）
例如，如果应用程序想要接受多种类型作为其 ResultSet 对象中的结果，它可以使用 ResultSet.getObject 方法。

ResultSet.getObject 和 CallableStatement.getObject 方法将值检索为 Java Object。由于 Object 是所有 Java 对象的基本类，因此可将任何 Java 类的实例检索为 Object 的实例。然而，以下 Java 类型是内置的“基本”类型，因此，它们不是类 Object 的实例： boolean、char、byte、short、int、long、 float 和 double。因此，不能用 getObject 方法来检索它们。然而，这些基本类型每种都有相应的可用作 wrapper 的类。这些类的实例是对象，这意味着可用 ResultSet.getObject 和 CallableStatement.getObject 方法来检索它们。第 67 页中的表 8.6.3 显示了从 JDBC 类型到 Java Object 类型的映射。该表与 JDBC 类型到 Java 类型的标准映射不同：在该表中，除了 JDBC TINYINT 和 JDBC SMALLINT 类型映射为 Java 类Integer 之外，每一个基本的 Java 类型都被替换为它们的 wrapper 类。

方法 getObject 还可用于检索用户定义的 Java 类型。随着抽象数据类型（ADT）和其它用户定义的类型在某些数据库系统中的出现，一些提供者可能会发现用 getObject 来检索这些类型将更方便。

8.6 数据类型映射表
本节含有以下表，它们是 JDBC 类型 和 Java 数据类型之间的映射关系表：

8.6.1 节 — 从 JDBC 类型映射到 Java 类型

8.6.2 节 — 从 Java 类型映射到 JDBC 类型

8.6.3 节 ─ 从 JDBC 类型映射到 Java Object 类型

8.6.4 节 ─ 从 Java Object 类型映射到 JDBC 类型

8.6.5 节 ─ 由 setObject 所进行的转换

8.6.6 节 — 由 ResultSet.getXXX 方法所检索的 JDBC 类型

8.6.1 从 JDBC 类型映射到 Java 类型
JDBC 类型	Java 类型
CHAR	String
VARCHAR	String
LONGVARCHAR	String
NUMERIC	java.math.BigDecimal
DECIMAL	java.math.BigDecimal
BIT	boolean
TINYINT	byte
SMALLINT	short
INTEGER	int
BIGINT	long
REAL	float
FLOAT	double
DOUBLE	double
BINARY	byte[]
VARBINARY	byte[]
LONGVARBINARY	byte[]
DATE	java.sql.Date
TIME	java.sql.Time
TIMESTAMP	java.sql.Timestamp



8.6.2 从 Java 类型映射到 JDBC 类型
该表显示的是表 8.6.1 的反映射：Java 类型到 JDBC 类型的映射。

Java 类型	JDBC 类型
String	VARCHAR 或 LONGVARCHAR
java.math.BigDecimal	NUMERIC
boolean	BIT
byte	TINYINT
short	SMALLINT
int	INTEGER
long	BIGINT
float	REAL
double	DOUBLE
byte[]	VARBINARY 或 LONGVARBINARY
java.sql.Date	DATE
java.sql.Time	TIME
java.sql.Timestamp	TIMESTAMP

 String 类型的映射通常是 VARCHAR，但如果所给的值超出了驱动程序对 VARCHAR 值所限定的极限，则将转换为 LONGVARCHAR 类型。对 byte[]、VARBINARY 及 LONGVARBINARY 值也一样。

8.6.3 从 JDBC 类型到 Java Object 类型的映射
由于 Java 内置类型（例如 boolean 和 int）不是 Object 的子类型，因此对于 getObject/setObject 方法，从 JDBC 类型到 Java object 类型的映射稍有不同。此种映射如下表所示：



JDBC 类型	Java Object 类型
CHAR	String
VARCHAR	String
LONGVARCHAR	String
NUMERIC	java.math.BigDecimal
DECIMAL	java.math.BigDecimal
BIT	Boolean
TINYINT	Integer
SMALLINT	Integer
INTEGER	Integer
BIGINT	Long
REAL	Float
FLOAT	Double
DOUBLE	Double
BINARY	byte[]
VARBINARY	byte[]
LONGVARBINARY	byte[]
DATE	java.sql.Date
TIME	java.sql.Time
TIMESTAMP	java.sql.Timestamp



8.6.4 Java Object 类型映射到 JDBC 类型
Java Object 类型	JDBC 类型
String	VARCHAR 或 LONGVARCHAR
java.math.BigDecimal	NUMERIC
Boolean	BIT
Integer	INTEGER
Long	BIGINT
Float	REAL
Double	DOUBLE
byte[]	VARBINARY 或 LONGVARBINARY
java.sql.Date	DATE
java.sql.Time	TIME
java.sql.Timestamp	TIMESTAMP

 注意，String 的映射通常为 VARCHAR，但如果所给的值超出了驱动程序对 VARCHAR 值所限定的极限值，则将转换为 LONGVARCHAR。对 byte[]、VARBINARY 和 LONGVARBINARY 值也一样。



8.6.5 由 setObject 所进行的转换
setObject



　	T
I
N
Y
I
N
T	S
M
A
L
L
I
N
T	I
N
T
E
G
E
R	B
I
G
I
N
T	R
E
A
L	F
L
O
A
T	D
O
U
B
L
E	D
E
C
I
M
A
L	N
U
M
E
R
I
C	B
I
T	C
H
A
R	V
A
R
C
H
A
R	L
O
N
G
V
A
R
C
H
A
R	B
I
N
A
R
Y	V
A
R
B
I
N
A
R
Y	L
O
N
G
V
A
R
B
I
N
A
R
Y	D
A
T
E	T
I
M
E	T
I
M
E
S
T
A
M
P
String	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x
java.math.BigDecimal	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
Boolean	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
Integer	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
Long	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
Float	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
Double	x	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
byte[]	　	　	　	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　
java.sql.Date	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	x	　	x
java.sql.Time	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	　	x	　
java.sql.Time- stamp	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	x	x	x

 从 Java object 类型到 JDBC 类型的转换。



8.6.6 由 ResultSet.getXXX 方法检索的 JDBC 类型
"x" 表示该方法可以检索 JDBC 类型。"X" 表示建议使用该方法来检索该 JDBC 类型。



　	T
I
N
Y
I
N
T	S
M
A
L
L
I
N
T	I
N
T
E
G
E
R	B
I
G
I
N
T	R
E
A
L	F
L
O
A
T	D
O
U
B
L
E	D
E
C
I
M
A
L	N
U
M
E
R
I
C	B
I
T	C
H
A
R	V
A
R
C
H
A
R	L
O
N
G
V
A
R
C
H
A
R	B
I
N
A
R
Y	V
A
R
B
I
N
A
R
Y	L
O
N
G
V
A
R
B
I
N
A
R
Y	D
A
T
E	T
I
M
E	T
I
M
E
S
T
A
M
P
getByte	X	x	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
getShort	x	X	x	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
getInt	x	x	X	x	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
getLong	x	x	x	X	x	x	x	x	x	x	x	x	x	　	　	　	　	　	　
getFloat	x	x	x	x	X	x	x	x	x	x	x	x	x	　	　	　	　	　	　
getDouble	x	x	x	x	x	X	X	x	x	x	x	x	x	　	　	　	　	　	　
getBigDecimal	x	x	x	x	x	x	x	X	X	x	x	x	x	　	　	　	　	　	　
getBoolean	x	x	x	x	x	x	x	x	x	X	x	x	x	　	　	　	　	　	　
getString	x	x	x	x	x	x	x	x	x	x	X	X	x	x	x	x	x	x	x
getBytes	　	　	　	　	　	　	　	　	　	　	　	　	　	X	X	x	　	　	　
getDate	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	X	　	x
getTime	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	　	X	x
getTimestamp	　	　	　	　	　	　	　	　	　	　	x	x	x	　	　	　	x	　	X
getAsciiStream	　	　	　	　	　	　	　	　	　	　	x	x	X	x	x	x	　	　	　
getUnicodeStream	　	　	　	　	　	　	　	　	　	　	x	x	X	x	x	x	　	　	　
getBinaryStream	　	　	　	　	　	　	　	　	　	　	　	　	　	x	x	X	　	　	　
getObject	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x


----

参考资料：
[1] https://www.cnblogs.com/shishm/archive/2012/01/30/2332142.html
