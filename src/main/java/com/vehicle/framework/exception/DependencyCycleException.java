package com.vehicle.framework.exception;

/**
 * @author HALOXIAO
 **/
public class DependencyCycleException extends RuntimeException {
    public DependencyCycleException(String message) {
        super(message);
    }

    public DependencyCycleException(String message, Throwable cause) {
        super(message, cause);
    }
}
