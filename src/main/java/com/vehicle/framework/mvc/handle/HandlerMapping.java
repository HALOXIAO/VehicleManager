package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.mvc.param.RequestChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author HALOXIAO
 **/
public class HandlerMapping implements Handler {


    public HandlerMapping(Set<Class<?>> controllerClasses) {

    }

    @Override
    public boolean handle(RequestChain requestChain) throws Exception {
        String path = requestChain.getRequestPath();
        String method = requestChain.getRequestMethod();
        HttpServletRequest request = requestChain.getHttpServletRequest();
        HttpServletResponse response = requestChain.getHttpServletResponse();
        return false;
    }



}
