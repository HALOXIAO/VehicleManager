package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.param.RequestChain;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class HandlerAdapter implements Handler {

    private int INIT_SIZE = 256;
    private final Set<Class<?>> controllerApplication = new HashSet<>(INIT_SIZE);


    public HandlerAdapter() {
        controllerApplication.addAll(getController());
    }

    @Override
    public Object handle(RequestChain requestChain) throws Exception {
        return true;
    }

    public Set<Class<?>> getControllerClasses() {
        return controllerApplication;
    }

    private Set<Class<?>> getController() {
        return BeanContainerFactory.getBeanContainer().getBeansByAnnotation(Controller.class);
    }


}

