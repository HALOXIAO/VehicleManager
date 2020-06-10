package com.vehicle.framework.exception;

/**
 * @author HALOXIAO
 **/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("ResourceNotFoundException:" + message);
    }
}
