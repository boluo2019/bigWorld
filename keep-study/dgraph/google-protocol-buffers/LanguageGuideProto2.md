````
Language Guide
Defining A Message Type
Scalar Value Types
Optional And Default Values
Enumerations
Using Other Message Types
Nested Types
Updating A Message Type
Extensions
Oneof
Maps
Packages
Defining Services
Options
Generating Your Classes
This guide describes how to use the protocol buffer language to structure your protocol buffer data, including .proto file syntax and how to generate data access classes from your .proto files. It covers the proto2 version of the protocol buffers language: for information on the newer proto3 syntax, see the Proto3 Language Guide.

Prefer Proto3 While proto2 will continue to be supported, we encourage new code to use proto3 instead, which is easier to use and supports more languages.

This is a reference guide – for a step by step example that uses many of the features described in this document, see the tutorial for your chosen language.

Defining A Message Type
First let's look at a very simple example. Let's say you want to define a search request message format, where each search request has a query string, the particular page of results you are interested in, and a number of results per page. Here's the .proto file you use to define the message type.

message SearchRequest {
  required string query = 1;
  optional int32 page_number = 2;
  optional int32 result_per_page = 3;
}

The SearchRequest message definition specifies three fields (name/value pairs), one for each piece of data that you want to include in this type of message. Each field has a name and a type.

Specifying Field Types
In the above example, all the fields are scalar types: two integers (page_number and result_per_page) and a string (query). However, you can also specify composite types for your fields, including enumerations and other message types.

Assigning Field Numbers
As you can see, each field in the message definition has a unique number. These numbers are used to identify your fields in the message binary format, and should not be changed once your message type is in use. Note that field numbers in the range 1 through 15 take one byte to encode, including the field number and the field's type (you can find out more about this in Protocol Buffer Encoding). Field numbers in the range 16 through 2047 take two bytes. So you should reserve the field numbers 1 through 15 for very frequently occurring message elements. Remember to leave some room for frequently occurring elements that might be added in the future.

The smallest field number you can specify is 1, and the largest is 229 - 1, or 536,870,911. You also cannot use the numbers 19000 through 19999 (FieldDescriptor::kFirstReservedNumber through FieldDescriptor::kLastReservedNumber), as they are reserved for the Protocol Buffers implementation - the protocol buffer compiler will complain if you use one of these reserved numbers in your .proto. Similarly, you cannot use any previously reserved field numbers.

Specifying Field Rules
You specify that message fields are one of the following:

required: a well-formed message must have exactly one of this field.
optional: a well-formed message can have zero or one of this field (but not more than one).
repeated: this field can be repeated any number of times (including zero) in a well-formed message. The order of the repeated values will be preserved.
For historical reasons, repeated fields of scalar numeric types aren't encoded as efficiently as they could be. New code should use the special option [packed=true] to get a more efficient encoding. For example:

repeated int32 samples = 4 [packed=true];

You can find out more about packed encoding in Protocol Buffer Encoding.

Required Is Forever You should be very careful about marking fields as required. If at some point you wish to stop writing or sending a required field, it will be problematic to change the field to an optional field – old readers will consider messages without this field to be incomplete and may reject or drop them unintentionally. You should consider writing application-specific custom validation routines for your buffers instead. Some engineers at Google have come to the conclusion that using required does more harm than good; they prefer to use only optional and repeated. However, this view is not universal.

Adding More Message Types
Multiple message types can be defined in a single .proto file. This is useful if you are defining multiple related messages – so, for example, if you wanted to define the reply message format that corresponds to your SearchResponse message type, you could add it to the same .proto:

message SearchRequest {
  required string query = 1;
  optional int32 page_number = 2;
  optional int32 result_per_page = 3;
}

message SearchResponse {
 ...
}

Combining Messages leads to bloat While multiple message types (such as message, enum, and service) can be defined in a single .proto file, it can also lead to dependency bloat when large numbers of messages with varying dependencies are defined in a single file. It's recommended to include as few message types per .proto file as possible.

Adding Comments
To add comments to your .proto files, use C/C++-style // and /* ... */ syntax.

/* SearchRequest represents a search query, with pagination options to
 * indicate which results to include in the response. */

message SearchRequest {
  required string query = 1;
  optional int32 page_number = 2;  // Which page number do we want?
  optional int32 result_per_page = 3;  // Number of results to return per page.
}

Reserved Fields
If you update a message type by entirely removing a field, or commenting it out, future users can reuse the field number when making their own updates to the type. This can cause severe issues if they later load old versions of the same .proto, including data corruption, privacy bugs, and so on. One way to make sure this doesn't happen is to specify that the field numbers (and/or names, which can also cause issues for JSON serialization) of your deleted fields are reserved. The protocol buffer compiler will complain if any future users try to use these field identifiers.

message Foo {
  reserved 2, 15, 9 to 11;
  reserved "foo", "bar";
}

Note that you can't mix field names and field numbers in the same reserved statement.

What's Generated From Your .proto?
When you run the protocol buffer compiler on a .proto, the compiler generates the code in your chosen language you'll need to work with the message types you've described in the file, including getting and setting field values, serializing your messages to an output stream, and parsing your messages from an input stream.

For C++, the compiler generates a .h and .cc file from each .proto, with a class for each message type described in your file.
For Java, the compiler generates a .java file with a class for each message type, as well as special Builder classes for creating message class instances.
Python is a little different – the Python compiler generates a module with a static descriptor of each message type in your .proto, which is then used with a metaclass to create the necessary Python data access class at runtime.
For Go, the compiler generates a .pb.go file with a type for each message type in your file.
You can find out more about using the APIs for each language by following the tutorial for your chosen language. For even more API details, see the relevant API reference.

Scalar Value Types
A scalar message field can have one of the following types – the table shows the type specified in the .proto file, and the corresponding type in the automatically generated class:
图片见 ![./google-protobuf-scalar-value-types.png]
You can find out more about how these types are encoded when you serialize your message in Protocol Buffer Encoding.

[1] In Java, unsigned 32-bit and 64-bit integers are represented using their signed counterparts, with the top bit simply being stored in the sign bit.

[2] In all cases, setting values to a field will perform type checking to make sure it is valid.

[3] 64-bit or unsigned 32-bit integers are always represented as long when decoded, but can be an int if an int is given when setting the field. In all cases, the value must fit in the type represented when set. See [2].


As mentioned above, elements in a message description can be labeled optional. A well-formed message may or may not contain an optional element. When a message is parsed, if it does not contain an optional element, the corresponding field in the parsed object is set to the default value for that field. The default value can be specified as part of the message description. For example, let's say you want to provide a default value of 10 for a SearchRequest's result_per_page value.

optional int32 result_per_page = 3 [default = 10];

If the default value is not specified for an optional element, a type-specific default value is used instead: for strings, the default value is the empty string. For bytes, the default value is the empty byte string. For bools, the default value is false. For numeric types, the default value is zero. For enums, the default value is the first value listed in the enum's type definition. This means care must be taken when adding a value to the beginning of an enum value list. See the Updating A Message Type section for guidelines on how to safely change definitions.

Enumerations
When you're defining a message type, you might want one of its fields to only have one of a pre-defined list of values. For example, let's say you want to add a corpus field for each SearchRequest, where the corpus can be UNIVERSAL, WEB, IMAGES, LOCAL, NEWS, PRODUCTS or VIDEO. You can do this very simply by adding an enum to your message definition - a field with an enum type can only have one of a specified set of constants as its value (if you try to provide a different value, the parser will treat it like an unknown field). In the following example we've added an enum called Corpus with all the possible values, and a field of type Corpus:

message SearchRequest {
  required string query = 1;
  optional int32 page_number = 2;
  optional int32 result_per_page = 3 [default = 10];
  enum Corpus {
    UNIVERSAL = 0;
    WEB = 1;
    IMAGES = 2;
    LOCAL = 3;
    NEWS = 4;
    PRODUCTS = 5;
    VIDEO = 6;
  }
  optional Corpus corpus = 4 [default = UNIVERSAL];
}

You can define aliases by assigning the same value to different enum constants. To do this you need to set the allow_alias option to true, otherwise protocol compiler will generate an error message when aliases are found.

enum EnumAllowingAlias {
  option allow_alias = true;
  UNKNOWN = 0;
  STARTED = 1;
  RUNNING = 1;
}
enum EnumNotAllowingAlias {
  UNKNOWN = 0;
  STARTED = 1;
  // RUNNING = 1;  // Uncommenting this line will cause a compile error inside Google and a warning message outside.
}

Enumerator constants must be in the range of a 32-bit integer. Since enum values use varint encoding on the wire, negative values are inefficient and thus not recommended. You can define enums within a message definition, as in the above example, or outside – these enums can be reused in any message definition in your .proto file. You can also use an enum type declared in one message as the type of a field in a different message, using the syntax MessageType.EnumType.

When you run the protocol buffer compiler on a .proto that uses an enum, the generated code will have a corresponding enum for Java or C++, or a special EnumDescriptor class for Python that's used to create a set of symbolic constants with integer values in the runtime-generated class.

For more information about how to work with message enums in your applications, see the generated code guide for your chosen language.

Reserved Values
If you update an enum type by entirely removing an enum entry, or commenting it out, future users can reuse the numeric value when making their own updates to the type. This can cause severe issues if they later load old versions of the same .proto, including data corruption, privacy bugs, and so on. One way to make sure this doesn't happen is to specify that the numeric values (and/or names, which can also cause issues for JSON serialization) of your deleted entries are reserved. The protocol buffer compiler will complain if any future users try to use these identifiers. You can specify that your reserved numeric value range goes up to the maximum possible value using the max keyword.

enum Foo {
  reserved 2, 15, 9 to 11, 40 to max;
  reserved "FOO", "BAR";
}

Note that you can't mix field names and numeric values in the same reserved statement.
Using Other Message Types
You can use other message types as field types. For example, let's say you wanted to include Result messages in each SearchResponse message – to do this, you can define a Result message type in the same .proto and then specify a field of type Result in SearchResponse:

message SearchResponse {
  repeated Result result = 1;
}

message Result {
  required string url = 1;
  optional string title = 2;
  repeated string snippets = 3;
}

Importing Definitions
In the above example, the Result message type is defined in the same file as SearchResponse – what if the message type you want to use as a field type is already defined in another .proto file?

You can use definitions from other .proto files by importing them. To import another .proto's definitions, you add an import statement to the top of your file:

import "myproject/other_protos.proto";

By default you can only use definitions from directly imported .proto files. However, sometimes you may need to move a .proto file to a new location. Instead of moving the .proto file directly and updating all the call sites in a single change, now you can put a dummy .proto file in the old location to forward all the imports to the new location using the import public notion. import public dependencies can be transitively relied upon by anyone importing the proto containing the import public statement. For example:

// new.proto
// All definitions are moved here

// old.proto
// This is the proto that all clients are importing.
import public "new.proto";
import "other.proto";

// client.proto
import "old.proto";
// You use definitions from old.proto and new.proto, but not other.proto

The protocol compiler searches for imported files in a set of directories specified on the protocol compiler command line using the -I/--proto_path flag. If no flag was given, it looks in the directory in which the compiler was invoked. In general you should set the --proto_path flag to the root of your project and use fully qualified names for all imports.

Using proto3 Message Types
It's possible to import proto3 message types and use them in your proto2 messages, and vice versa. However, proto2 enums cannot be used in proto3 syntax.
````