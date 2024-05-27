package com.example;

public final class SportCarNative {
    public static native void init(long isolateThreadId);
    public static native int add(long isolateThread, int a, int b);
    public static native long createIsolate();
}
