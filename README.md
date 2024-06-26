## GraalVM JNI Invocation Sample

This sample uses the [JNI Invocation API](https://www.graalvm.org/sdk/jni/) to call a native function from Java;
the native function is implemented itself in Java, and is compiled into a shared library that is loaded and then invoked
by Java code.

### Usage

**1) Compile the shared library.** This will create `libnativeimpl.dylib` in `lib/build/native/nativeCompile`.
```
./gradlew :lib:nativeCompile
```

**2) Run the outer entrypoint.** This will run the JVM application, which loads and runs the library.
```
./gradlew run
```

**3) Observe output.** If you see the following, everything is working:
```
SportCarNative initialized (isolate: <a long number>)
```

### Structure

See [`SportCarNative.java`](./lib/src/main/java/com/example/SportCarNative.java) for the native interface provided by
the shared library.

This effectively becomes a C/C++ header file, which looks like this (generated by GraalVM, as part of the build):
```
// ... shortened for brevity ...
int run_main(int argc, char** argv);

graal_isolatethread_t* Java_com_example_SportCarNative_createIsolate();

void Java_com_example_SportCarNative_init(size_t, size_t, long long int);
```

Then, the corresponding JNI layout at [`SportCarNative.java`](./src/main/java/com/example/SportCarNative.java), which
resides at the same package/class path but in the main module, explains this native library layout via JNI.

The library can then be loaded (as it is in [`DemoCommand.kt`](./src/main/kotlin/com/example/DemoCommand.kt)) and used
via JNI-based calls.

### Generated via Micronaut

This project was generated via the [Micronaut Launch](https://launch.micronaut.io) tool, using the following options:

- Template: Command-Line App via Picocli
- Enabled `kapt` (Picocli does not support KSP yet)
- Enabled `graalvm` feature for native builds
