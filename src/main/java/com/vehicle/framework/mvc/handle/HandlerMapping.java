package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.module.ControllerInfo;
import com.vehicle.framework.mvc.module.PathInfo;
import com.vehicle.framework.mvc.param.RequestChain;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HALOXIAO
 **/
public class HandlerMapping implements Handler {

    private int INIT_SIZE = 256;
    private Set<Class<?>> controllerClasses;
    private final Map<PathInfo, ControllerInfo> controllerMap = new ConcurrentHashMap<>(INIT_SIZE);

    public HandlerMapping(Set<Class<?>> controllerClasses) {
        this.controllerClasses = controllerClasses;
    }

    @Override
    public boolean handle(RequestChain requestChain) throws Exception {
        settingControllerMap(requestChain);
        return false;
    }
//TODO 继续搞
    private void settingControllerMap(RequestChain requestChain) {
        controllerClasses.stream().forEach(
                clz -> {

                    String firstPath = clz.getAnnotation(RequestMapping.class).value();
                    String secondPath = "";
                    for (Method method : clz.getDeclaredMethods()) {
                        secondPath = method.getDeclaredAnnotation(RequestMapping.class).value();
                    }
                    String requestPath = firstPath + secondPath;
                    String httpMethod = requestChain.getRequestMethod();
                    PathInfo pathInfo = new PathInfo(httpMethod, requestPath);

                }
        );

    }

    private void invoke(RequestChain requestChain) {

    }


}
