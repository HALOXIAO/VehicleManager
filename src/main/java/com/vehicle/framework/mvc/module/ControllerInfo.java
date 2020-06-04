package com.vehicle.framework.mvc.module;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author HALOXIAO
 **/
@Data
public class ControllerInfo {

    private Class<?> controllerClz;

    private Map<String, Method> maps;




}
