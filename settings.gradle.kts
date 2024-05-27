pluginManagement {
  repositories {
    maven {
      name = "gvm-plugin-snapshots"
      url = uri("https://raw.githubusercontent.com/graalvm/native-build-tools/snapshots")
    }
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

rootProject.name="demo"

include(":lib")
