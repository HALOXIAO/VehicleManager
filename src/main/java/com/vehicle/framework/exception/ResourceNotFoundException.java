package com.vehicle.framework.exception;

/**
 * @author HALOXIAO
 **/
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super("ResourceNotFoundException:" + message);
    }
}
