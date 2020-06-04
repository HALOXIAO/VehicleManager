package com.vehicle.framework.exception;

/**
 * @author HALOXIAO
 **/
public class AnnotationException extends RuntimeException {
    public AnnotationException(String message) {
        super(message);
    }

    public AnnotationException(String message, Throwable cause) {
        super(message, cause);
    }
}

