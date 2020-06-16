package com.vehicle.business.module.convert;

import com.vehicle.business.module.Route;
import com.vehicle.business.module.param.RouteParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;

import java.util.List;

/**
 * @author HALOXIAO
 **/
public class RouteParamToRoute {

    public static Route toRoute(RouteParam routeParam) {
        Route route = new Route();
        route.setName(routeParam.getName());
        route.setDetail(routeDetailTransform(routeParam.getStationIds()));
        route.setStartId(routeParam.getStationIds().get(0));
        route.setEndId(routeParam.getStationIds().get(routeParam.getStationIds().size() - 1));
        route.setStartId(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        return route;
    }


    public static String routeDetailTransform(List<Integer> routeDetail) {
        StringBuilder builder = new StringBuilder(routeDetail.size() * 2 - 1);
        routeDetail.forEach(station -> {
            builder.append(station).append(",");
        });
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }
}
