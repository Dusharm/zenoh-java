package io.zenoh.exceptions;

/**
 * Represents an error reported by the native Zenoh layer.
 */
public class ZError extends Exception {
    public ZError() {
        super();
    }

    public ZError(String message) {
        super(message);
    }
}
