package com.vehicle.business.service;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.vehicle.business.common.util.CollectionUtils;
import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.RouteParam;
import com.vehicle.business.module.param.RouteUpdatedParam;
import com.vehicle.business.module.vo.RouteVO;
import com.vehicle.business.module.vo.StationVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;

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
        List<Station> stations = stationMapper.getStationPage(idSet);
        ImmutableMap<Integer, StationVO> idStationVOMap = CollectionUtils.toMap(stations.iterator(), new Function<Station, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Station input) {
                return input.getId();
            }
        }, new Function<Station, StationVO>() {
            @Nullable
            @Override
            public StationVO apply(@Nullable Station input) {
                StationVO stationVO = new StationVO();
                stationVO.setId(input.getId());
                stationVO.setName(input.getName());
                stationVO.setAddress(input.getAddress());
                return stationVO;
            }
        });
        List<RouteVO> resultList = new LinkedList<>();
        routeList.forEach(route -> {
            RouteVO routeVO = new RouteVO();
            routeVO.setId(route.getId());
            routeVO.setName(route.getName());
            String[] stationTemp = route.getDetail().split(",");
            List<StationVO> list = new ArrayList<>(stationTemp.length);
            for (int i = 0; i < stationTemp.length; i++) {
                list.add(idStationVOMap.get(Integer.valueOf(stationTemp[i])));
            }
            routeVO.setRouteDetail(list);
            resultList.add(routeVO);
        });
        return resultList;
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

    private boolean checkStationExist(RouteParam routeParam) {
        return stationMapper.checkStationsExist();
    }


}
