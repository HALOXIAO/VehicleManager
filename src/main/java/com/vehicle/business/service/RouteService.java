package com.vehicle.business.service;

import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.RouteParam;
import com.vehicle.business.module.param.RouteUpdatedParam;
import com.vehicle.business.module.vo.RouteVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HALOXIAO
 **/
@Service
public class RouteService {

    @Autowired
    RouteMapper routeMapper;

    @Autowired
    StationMapper stationMapper;

    public List<RouteVO> getRoutePage(PageParam pageParam) {
        List<Route> routeList = routeMapper.getRoutePage(pageParam);
        Set<String> idSet = new HashSet<>();
        routeList.forEach(route -> {
            String[] stationIds = route.getDetail().split(",");
            idSet.addAll(Arrays.asList(stationIds));
        });

//        stationMapper.getStationPage();
        return null;
    }

    public Route addRoute(RouteParam routeParam) {
        return null;
    }

    public RouteVO getRoute(Integer id) {
        return null;
    }

    public boolean updateRoute(RouteUpdatedParam routeParam) {
        return false;
    }

    public boolean deleteRoutes(List<Integer> ids) {
        return false;

    }

    private String detailTransform(List<Integer> routeDetail) {
        return null;
    }

    public int routePageCount() {
        return 0;
    }


}
