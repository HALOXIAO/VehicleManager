package com.vehicle.comon;

/**
 * @author HALOXIAO
 **/
public enum HTTP_METHOD {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");


    private String value;

    HTTP_METHOD(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
