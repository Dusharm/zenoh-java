package io.zenoh.jni;

import io.zenoh.Config;
import io.zenoh.ZenohLoad;
import io.zenoh.exceptions.ZError;

/**
 * Minimal JNI bridge for a Zenoh session. Only session creation and
 * destruction are provided in this Java 8 variant.
 */
public class JNISession {
    private final long sessionPtr;

    private JNISession(long ptr) {
        this.sessionPtr = ptr;
    }

    static {
        ZenohLoad.ensureLoaded();
    }

    /**
     * Open a new session using the supplied configuration.
     */
    public static JNISession open(Config config) throws ZError {
        long ptr = openSessionViaJNI(config.jniConfig().getPtr());
        return new JNISession(ptr);
    }

    /** Close the session and release native resources. */
    public void close() {
        closeSessionViaJNI(sessionPtr);
    }

    private static native long openSessionViaJNI(long configPtr) throws ZError;
    private static native void closeSessionViaJNI(long sessionPtr);
}
