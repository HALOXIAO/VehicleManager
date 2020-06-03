package com.vehicle.framework.exception;

/**
 * @author HALOXIAO
 **/
public class ClassNotLoadException extends RuntimeException {

    public ClassNotLoadException(String message) {
        super(message);
    }

    public ClassNotLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
