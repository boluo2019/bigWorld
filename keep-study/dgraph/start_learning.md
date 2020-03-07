BadgerDB
Advocate
- grpc: gRPC is a modern open source high performance RPC framework that can run in any environment. 
It can efficiently connect services in and across data centers with pluggable support for load balancing, 
tracing, health checking and authentication. It is also applicable in last mile of distributed computing 
to connect devices, mobile applications and browsers to backend services.

##### protocol buffers : protobuf
- 是什么？ 
    - 是一种机制，有语言无关的、平台无关的、可扩展的特性，用来序列化结构化的数据。
    - 结构化数据表示格式。
- 类似技术？
    - XML
        - 多种行业标准的编写工具
        - 具有自解释性，可以直接读取编辑
    - JSON
    - Thrift 
    - protobuf的相比以上技术的优点：简单，快
    - 灵魂拷问？为甚先出现xml、json、thrift，最后才出现protobuf？
    - 缺点
        - 没有被大规模使用，通用性较差
        - 不适合对基于文本的标记文档(eg: HTML)建模
        - 二进制文件存储，需要.proto文件来读取
    - 两项技术使得protobuf比xml快3~100倍
        - protobuf序列化后的protocol buffer信息内容非常紧凑。所以体积小，网络传输字节小，需要的IO也就更少。
        - 理解protobuf封装包的大致过程就可以理解比xml快的原因。
            - varint编码
                - varint中每个byte的最高位bit有特殊含义，如果该位是1表示后续的byte也是该数字的一部分，如果该位为0，表示该数字结束。其他的7个bit都用来表示数字。
                - 传统int需要32bit共4个字节表示，但是当数值小鱼256时，只需1个字节，可以节省3个字节，但是比较大的数字，需要5个byte，因为每个byte中只有7bit用来表示数字。
            - little-endian字节序列[images/google-protobuf-varint.png]
            - varchar: varint+chars
            - zigzag编码方式（无符号）
                - 为什么int32/64表示一个负数需要10个字节？？？因为计算机内一个负数会被表示为一个很大的整数，如果varint表示负数，那一定要5byte，所以Google protocol buffer定义了sint32这种类型，采用zigzag编码。
                - sint32/64,将有符号整型映射为无符号整型
                - 算术移位（算术移位与逻辑移位的区别：左移都相同，即低位补0，右移不同：逻辑移位高位补0，算术移位正数补0负数补1）
                    - n表示int32/64的varint，下面是计算sint32/64的公式： 
                    - (n << 1) ^ (n >> 31) //sint32 左移1位为了去掉varint的标志位，右算术移31位，为了解决
                    - (n << 1> ^ (n >> 63) //sint64
            - 消息经过序列化后会成为一个二进制的数据流，该流中的数据为一系列的key-value对。[images/protobuf-message-key-value.jpg]
              对于可选field，如果该field没有值，最终的message buffer中就没有的该field。
            - key由两部分组成：(field_number << 3) | wire_type。 field_number表示proto中定义的field的序号，wire_type表示value的传输类型
- 什么时候需要用？
    - RPC系统
    - 持续数据存储系统
- 什么语言实现？ C++
- 怎么用？ 
    - 编写.proto文件，下载对应语言的已编译文件安装protoc，通过protoc编译proto文件生成对应语言的代码，用来读写这个数据结构。
        - 对于java
        - python
        - go
        - 生成代码的流程：语法树
    - 如果通过无需重新部署程序的方式更新数据结构？？
    - 高级使用方式
        - 嵌套Message
        - import Message：引入其他.proto文件中定义的消息
        - 动态编译：动态生成proto文件
    
- protobuf消息解码过程：
    - 
##### grpc


-[grpc官网](https://www.grpc.io/faq/)

#### dgraph相关的中文资料：
- [开源图数据库项目 DGraph 的前世今生](https://baijiahao.baidu.com/s?id=1628331109232303412&wfr=spider&for=pc)
-[Google Protocol Buffer 的使用和原理](https://www.ibm.com/developerworks/cn/linux/l-cn-gpb/)
-[]()
-[]()
-[]()
