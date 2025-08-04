package io.zenoh;

import io.zenoh.exceptions.ZError;
import io.zenoh.jni.JNIConfig;
import java.io.File;
import java.nio.file.Path;

/**
 * Java representation of a Zenoh configuration.
 */
public class Config {
    private final JNIConfig jniConfig;

    Config(JNIConfig jniConfig) {
        this.jniConfig = jniConfig;
    }

    public static Config loadDefault() {
        return JNIConfig.loadDefaultConfig();
    }

    public static Config fromFile(File file) throws ZError {
        return JNIConfig.loadConfigFile(file);
    }

    public static Config fromFile(Path path) throws ZError {
        return JNIConfig.loadConfigFile(path);
    }

    public static Config fromJson(String config) throws ZError {
        return JNIConfig.loadJsonConfig(config);
    }

    public static Config fromJson5(String config) throws ZError {
        return JNIConfig.loadJson5Config(config);
    }

    public static Config fromYaml(String config) throws ZError {
        return JNIConfig.loadYamlConfig(config);
    }

    public String getJson(String key) throws ZError {
        return jniConfig.getJson(key);
    }

    public void insertJson5(String key, String value) throws ZError {
        jniConfig.insertJson5(key, value);
    }

    @Override
    protected void finalize() throws Throwable {
        jniConfig.close();
        super.finalize();
    }

    JNIConfig jniConfig() {
        return jniConfig;
    }
}
