package com.example;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.word.Pointer;

public class SportCarNative implements SportCar {
  @Override
  public void start() {
    System.out.println("SportCar started");
  }

  @CEntryPoint(name = "Java_com_example_SportCarNative_init")
  public static void init(Pointer jniEnv, Pointer clazz, @CEntryPoint.IsolateThreadContext long isolateId) {
    System.out.println("SportCarNative initialized (isolate: " + String.valueOf(isolateId) + ")");
  }

  @CEntryPoint(name = "Java_com_example_SportCarNative_createIsolate", builtin = CEntryPoint.Builtin.CREATE_ISOLATE)
  public static native IsolateThread createIsolate();

  public static void main(String[] args) {
    System.out.println("SportCarNative main");
  }
}
