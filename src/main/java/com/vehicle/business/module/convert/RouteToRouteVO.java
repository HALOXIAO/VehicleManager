package com.vehicle.business.module.convert;

import com.vehicle.business.module.Route;
import com.vehicle.business.module.vo.RouteVO;
import com.vehicle.business.module.vo.StationVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author HALOXIAO
 **/
public class RouteToRouteVO {



    public static RouteVO toRouteVO(Route route, Map<Integer, StationVO> stationMap){
        RouteVO routeVO = new RouteVO();
        routeVO.setId(route.getId());
        routeVO.setName(route.getName());
        String[] stationTemp = route.getDetail().split(",");
        List<StationVO> list = new ArrayList<>(stationTemp.length);
        for (int i = 0; i < stationTemp.length; i++) {
            list.add(stationMap.get(Integer.valueOf(stationTemp[i])));
        }
        routeVO.setRouteDetail(list);
        return routeVO;
    }


}
