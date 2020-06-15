package com.vehicle.business.common.exception;

/**
 * @author HALOXIAO
 **/
public class DataBaseRelationshipException extends RuntimeException {

    public DataBaseRelationshipException(String msg) {
        super("DataBaseRelationshipException：" + msg);
    }

    public DataBaseRelationshipException(String msg, Throwable cause) {
        super("DataBaseRelationshipException：" + msg, cause);
    }
}
