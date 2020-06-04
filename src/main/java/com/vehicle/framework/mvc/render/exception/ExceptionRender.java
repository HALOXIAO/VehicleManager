package com.vehicle.framework.mvc.render.exception;

import com.vehicle.framework.mvc.param.RequestChain;

/**
 * @author HALOXIAO
 **/
public interface ExceptionRender {

    void handler(RequestChain requestChain,Exception e);

}
