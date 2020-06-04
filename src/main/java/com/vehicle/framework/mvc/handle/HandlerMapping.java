package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.exception.AnnotationException;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;
import com.vehicle.framework.mvc.module.ControllerInfo;
import com.vehicle.framework.mvc.module.PathInfo;
import com.vehicle.framework.mvc.param.RequestChain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        settingControllerMap();
        return false;
    }

    //TODO 继续搞
    private void settingControllerMap() {
        controllerClasses.stream().forEach(
                clz -> {
                    String firstPath = "";
                    if (clz.isAnnotationPresent(RequestMapping.class)) {
                        firstPath = clz.getAnnotation(RequestMapping.class).value();
                    }
                    String secondPath = "";
                    String requestPath = "";
                    for (Method method : clz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            secondPath = method.getDeclaredAnnotation(RequestMapping.class).value();
                            requestPath = firstPath + secondPath;
                            int _PCount = 0;
                            int _BCount = 0;
                            Map<String, Class<?>> methodParameter = new ConcurrentHashMap<>(5);
                            for (Parameter parameter : method.getParameters()) {

                                if (parameter.isAnnotationPresent(RequestParam.class)) {
                                    _PCount++;
                                    Class<?> parameterType = parameter.getType();
                                    String name = "".equals(parameter.getDeclaredAnnotation(RequestParam.class).value()) ? parameter.getName() : parameter.getDeclaredAnnotation(RequestParam.class).value();
                                    methodParameter.put(name, parameterType);
                                } else if (parameter.isAnnotationPresent(RequestBody.class)) {
                                    _BCount++;
                                }

                            }
                            if (_PCount > 0 && _BCount > 0) {
                                throw new AnnotationException("@RequestParam and @RequestBody can not be together ");
                            }
                            ControllerInfo controllerInfo = new ControllerInfo(clz, method, methodParameter);
                            PathInfo pathInfo = new PathInfo(method.getDeclaredAnnotation(RequestMapping.class).method(), requestPath);


                        }
                    }

                }
        );

    }

    private void invoke(RequestChain requestChain) {

    }


}
