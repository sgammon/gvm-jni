plugins {
  java
  `java-library`
  id("org.graalvm.buildtools.native")
}

java {
  sourceCompatibility = JavaVersion.toVersion("21")
  targetCompatibility = JavaVersion.toVersion("21")
}

graalvmNative {
  binaries {
    named("main") {
      sharedLibrary = true
      imageName = "libnativeimpl"
      buildArgs(
        "--initialize-at-build-time=",
        "-H:Class=com.example.SportCarNative",
      )
    }
  }
}

dependencies {
  compileOnly("org.graalvm.nativeimage:svm:24.0.1")
}
