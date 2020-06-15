package com.vehicle.business.common.exception;


/**
 * @author HALOXIAO
 **/
public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String msg) {
        super("BusinessLogicException：" + msg);
    }

    public BusinessLogicException(String msg, Throwable cause) {
        super("BusinessLogicException：" + msg, cause);
    }
}
