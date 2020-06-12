package com.vehicle.common.status;

/**
 * @author HALOXIAO
 **/
public enum DATABASE_ROUTE_STATUS_CODE {
    DELETE(0),
    NORMAL(1);

    DATABASE_ROUTE_STATUS_CODE(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }
}
