package io.zenoh;

/**
 * Utility class that loads the native Zenoh JNI library once.
 * <p>
 * The library is expected to be available as <code>libzenoh_jni.so</code>
 * in the system library path.
 */
public final class ZenohLoad {
    private static final boolean LOADED;

    static {
        System.loadLibrary("zenoh_jni");
        LOADED = true;
    }

    private ZenohLoad() {
        // no instances
    }

    /**
     * Ensure the native library is loaded. Invoking this method triggers the
     * class initialisation and therefore the static loading block.
     */
    public static void ensureLoaded() {
        // method intentionally left blank
    }
}
