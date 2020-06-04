package com.vehicle.framework.mvc.handle;

import com.vehicle.framework.mvc.param.RequestChain;

/**
 * @author HALOXIAO
 **/
public interface Handler {
    Object handle(RequestChain requestChain) throws Exception;
}
