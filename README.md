Wire Mobile Protocol Buffers
============================

*“A man got to have a code!”* - Omar Little


Introduction
------------

Wire is a library for lightweight protocol buffers for mobile Java. Code generated by Wire has many
fewer methods than standard protocol buffer code, which helps applications avoid the notorious 64k
limit on methods in Android applications. Wire also generates clean, human-readable code for
protocol buffer messages.

Wire is built using the [Maven](http://maven.apache.org) build system.

Compiling .proto files
----------------------

Build the wire-compiler using Maven (alternatively you can just download 
[the wire-compiler .jar][dl_compiler]):

    % mvn clean package

The `wire-compiler` package contains the `WireCompiler` class, which compiles standard `.proto` files
into Java source code.

For example, to compile the file `protos-repo/google/protobuf/descriptor.proto`, which may
(recursively) import other `.proto` files within the `protos-repo/` directory (replace
"VERSION" with the Wire version you are using):

    % java -jar wire-compiler/target/wire-compiler-VERSION-jar-with-dependencies.jar \
        --proto_path=protos-repo \
        --java_out=out google/protobuf/descriptor.proto

    Reading proto source file protos-repo/google/protobuf/descriptor.proto
    Writing generated code to out/com/google/protobuf/DescriptorProtos.java

    % head -11 out/com/google/protobuf/DescriptorProto.java
    // Code generated by Wire protocol buffer compiler, do not edit.
    // Source file: protos-repo/google/protobuf/descriptor.proto
    package com.google.protobuf;

    import com.squareup.wire.Message;
    import com.squareup.wire.ProtoField;
    import java.util.Collections;
    import java.util.List;

    public final class DescriptorProto
        implements Message {

Instead of supplying individual filename arguments on the command line, the `--files` flag may be
used to specify a single file containing a list of `.proto` files. The file names are interpreted
relative to the value given for the `--proto_path` flag.

    % cat protos.include
    google/protobuf/descriptor.proto
    yourcompany/protos/stuff.proto
    ...

    % java -jar wire-compiler/target/wire-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar \
        --proto_path=protos-repo \
        --java_out=out \
        --files=protos.include

    Reading proto source file protos-repo/google/protobuf/descriptor.proto
    Writing generated code to out/com/google/protobuf/DescriptorProtos.java
    Reading proto source file protos-repo/yourcompany/protos/stuff.proto
    Writing generated code to out/com/yourcompany/protos/stuff/Stuff.java
    ...

The compiler will (recursively) import any needed `.proto` files from the `protos-repo/` directory, but
will only generate output for the `.proto` files listed on the command line or in the file specified
by the `--files` flag.

Using Wire in your application
------------------------------

The `wire-runtime` package contains runtime support libraries that must be included in applications
that use Wire-generated code.

Download [the latest runtime .jar][dl_runtime] or depend via Maven:

```xml
<dependency>
  <groupId>com.squareup.wire</groupId>
  <artifactId>wire-runtime</artifactId>
  <version>(latest version)</version>
</dependency>
```

If you use Proguard, then you need to add `keep` rules.  The simplest option is to tell Proguard not to touch the Wire library and your generated protocol buffers (of course these simple rules will miss opportunities to shrink and optimize the code):

    -keep class com.squareup.wire.** { *; }    
    -keep class com.yourcompany.yourgeneratedcode.** { *; }
 
How Wire works
--------------

The Wire compiler generates a Java class for each message or enum defined in a `.proto file` specified
on the command line. Each message class has an associated Builder class that may be used to construct
an instance manually:

    MyMessage msg = new MyMessage.Builder().some_int_field(123).build();

Note that field names are not converted to camel case.

Wire messages contain a `public final` field for each field of the protocol buffer message.
Each field is annotated with a `@ProtoField` annotation containing the field metadata required
by the Wire runtime.

Numeric and boolean values are stored using boxed primitive types (e.g., Integer or Long).
If a field is unset, its value is `null`. Wire does not generate methods such as `getXXX()`,
`hasXXX()`, `setXXX(`), etc. Repeated fields are stored as Lists of values.

A field `some_field` has a constant `DEFAULT_SOME_FIELD` containing the default value for that
field. A convenience method `Wire.get` allows substitution of a default value for `null`:

```java
// Equivalent to:
// x = msg.some_field != null ? msg.some_field :  MyMessage.DEFAULT_SOME_FIELD

int x = Wire.get(msg.some_field, MyMessage.DEFAULT_SOME_FIELD);
```

Builders contain a `public` field for each field of the protocol buffer message, as well as
a method with the same name that sets the given value and returns the Builder instance for
chaining.

You can serialize a message by calling its `write` or `toByteArray` methods:

```java
byte[] serializedMsg = msg.toByteArray();
```

To parse messages from their serialized representations, use the `Wire` class. Typically you
will want to create a singleton instance of `Wire` for use throughout your application.

```java
Wire wire = new Wire();
MyMessage newMsg = wire.parseFrom(serializedMsg, MyMessage.class);
int x = newMsg.some_int_field; // 123
```

To use protocol buffer extensions, pass the classes that define the extensions you
wish to use as arguments to the `Wire` constructor:

```java
// Assume MessageWithExtensions contains a message SomeMessage that defines
// an extension field some_extension to the MyMessage message.
Wire wire = new Wire(Ext_SomeMessage.class);
MyMessage msg = new MyMessage.Builder()
    .setExtension(Ext_SomeMessage.some_extension, 3)
    .build();
int x = msg.getExtension(Ext_SomeMessage.some_extension); // 3
```

Unsupported
-----------

Wire does not support:

 * Groups - they are skipped when parsing binary input data
 * Services - they are ignored by the compiler

Wire supports custom options on messages and fields. Other custom options are ignored. Use the `--no_options` flag to omit option information from the generated code.

 [dl_runtime]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.squareup.wire&a=wire-runtime&v=LATEST
 [dl_compiler]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.squareup.wire&a=wire-compiler&v=LATEST&c=jar-with-dependencies
