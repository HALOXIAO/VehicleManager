package com.vehicle.framework.mvc.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author HALOXIAO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControllerInfo {

    private Class<?> controllerClz;

    /**
     * 调用method
     * */
    private Method invokeMethod;

    /**
     * param
     * */
    private Map<String, Class<?>> methodParameter;


}
