package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.mvc.param.RequestChain;

/**
 * @author HALOXIAO
 **/
public interface Handler {
    boolean handle(RequestChain requestChain) throws Exception;
}
