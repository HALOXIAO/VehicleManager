package com.vehicle.business.common.exception;

/**
 * @author HALOXIAO
 **/
public class ParamException extends RuntimeException {

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

}
