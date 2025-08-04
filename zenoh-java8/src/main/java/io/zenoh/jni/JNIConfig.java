package io.zenoh.jni;

import io.zenoh.Config;
import io.zenoh.ZenohLoad;
import io.zenoh.exceptions.ZError;
import java.io.File;
import java.nio.file.Path;

/**
 * JNI bridge for Zenoh configuration handling.
 */
public class JNIConfig {
    private final long ptr;

    JNIConfig(long ptr) {
        this.ptr = ptr;
    }

    static {
        ZenohLoad.ensureLoaded();
    }

    public static Config loadDefaultConfig() {
        long cfgPtr = loadDefaultConfigViaJNI();
        return new Config(new JNIConfig(cfgPtr));
    }

    public static Config loadConfigFile(Path path) throws ZError {
        long cfgPtr = loadConfigFileViaJNI(path.toString());
        return new Config(new JNIConfig(cfgPtr));
    }

    public static Config loadConfigFile(File file) throws ZError {
        return loadConfigFile(file.toPath());
    }

    public static Config loadJsonConfig(String rawConfig) throws ZError {
        long cfgPtr = loadJsonConfigViaJNI(rawConfig);
        return new Config(new JNIConfig(cfgPtr));
    }

    public static Config loadJson5Config(String rawConfig) throws ZError {
        long cfgPtr = loadJson5ConfigViaJNI(rawConfig);
        return new Config(new JNIConfig(cfgPtr));
    }

    public static Config loadYamlConfig(String rawConfig) throws ZError {
        long cfgPtr = loadYamlConfigViaJNI(rawConfig);
        return new Config(new JNIConfig(cfgPtr));
    }

    public void close() {
        freePtrViaJNI(ptr);
    }

    public String getJson(String key) throws ZError {
        return getJsonViaJNI(ptr, key);
    }

    public void insertJson5(String key, String value) throws ZError {
        insertJson5ViaJNI(ptr, key, value);
    }

    long getPtr() {
        return ptr;
    }

    private static native long loadDefaultConfigViaJNI();
    private static native long loadConfigFileViaJNI(String path) throws ZError;
    private static native long loadJsonConfigViaJNI(String rawConfig) throws ZError;
    private static native long loadJson5ConfigViaJNI(String rawConfig) throws ZError;
    private static native long loadYamlConfigViaJNI(String rawConfig) throws ZError;
    private static native void insertJson5ViaJNI(long ptr, String key, String value) throws ZError;
    private static native String getJsonViaJNI(long ptr, String key) throws ZError;
    private static native void freePtrViaJNI(long ptr);
}
