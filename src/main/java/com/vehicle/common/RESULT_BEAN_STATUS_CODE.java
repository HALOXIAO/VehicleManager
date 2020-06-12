package com.vehicle.common;

import lombok.Getter;

/**
 * @author HALOXIAO
 **/

public enum RESULT_BEAN_STATUS_CODE {

    //未登录
    NO_LOGIN(-1),
    //成功
    SUCCESS(0),
    //检查失败
    CHECK_FAIL(1),
    //无权限
    NO_PERMISSION(2),
    //未知异常
    UNKNOWN_EXCEPTION(-99),
    //参数异常
    ARGUMENT_EXCEPTION(3);


    private final int code;

    RESULT_BEAN_STATUS_CODE(final int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
