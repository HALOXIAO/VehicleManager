package com.vehicle.business.module.convert;

import com.vehicle.business.common.exception.ParamException;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.param.RouteUpdatedParam;

import java.util.List;

/**
 * @author HALOXIAO
 **/
public class RouteUpParamToRoute {

    public static Route toRoute(RouteUpdatedParam param) {
        List<Integer> detail = param.getDetail();
        if (detail == null || detail.size() == 0) {
            throw new ParamException("RouteUpdatedParam#detail has exception");
        }
        Route route = new Route();
        route.setId(param.getId());
        route.setName(param.getName());
        route.setStartId(detail.get(0));
        route.setEndId(detail.get(detail.size() - 1));
        return route;
    }
}
