package com.vehicle.business.service;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.vehicle.business.common.util.CollectionUtils;
import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.convert.RouteParamToRoute;
import com.vehicle.business.module.convert.RouteToRouteVO;
import com.vehicle.business.module.convert.RouteUpParamToRoute;
import com.vehicle.business.module.convert.StationToStationVO;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.RouteParam;
import com.vehicle.business.module.param.RouteUpdatedParam;
import com.vehicle.business.module.vo.RouteVO;
import com.vehicle.business.module.vo.StationVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * @author HALOXIAO
 **/
@Service
public class RouteService {

    @Autowired
    public RouteMapper routeMapper;

    @Autowired
    public StationMapper stationMapper;

    public List<RouteVO> getRoutePage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        session.setDefaultReadOnly(true);
        List<Route> routeList = routeMapper.getRoutePage(pageParam, session);
        Set<String> idSet = new HashSet<>();
        routeList.forEach(route -> {
            String[] stationIds = route.getDetail().split(",");
            idSet.addAll(Arrays.asList(stationIds));
        });
        List<Station> stations = stationMapper.getStationPage(idSet, session);
        SessionUtils.subsequentProcessing(session);
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
            resultList.add(RouteToRouteVO.toRouteVO(route, idStationVOMap));
        });

        return resultList;
    }

    public Route addRoute(RouteParam routeParam) {
        Route route = RouteParamToRoute.toRoute(routeParam);
        routeMapper.addRoute(route);
        return route;
    }

    public RouteVO getRoute(Integer id) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.beginTransaction();
        session.setDefaultReadOnly(true);
        Route route = routeMapper.getRoute(id, session);
        RouteVO result = new RouteVO();
        result.setId(result.getId());
        result.setName(route.getName());
        String[] stations = route.getDetail().split(",");
        HashSet<String> ids = new HashSet<>((int) (stations.length / 0.75f) + 1);
        ids.addAll(Arrays.asList(stations));
        List<Station> stationList = stationMapper.getStationPage(ids, session);
        List<StationVO> stationVOList = StationToStationVO.INSTANCE.toStationVOList(stationList);
        result.setRouteDetail(stationVOList);
        SessionUtils.subsequentProcessing(session);
        return result;
    }

    public boolean updateRoute(RouteUpdatedParam routeParam) {
        routeMapper.updateRoute(RouteUpParamToRoute.toRoute(routeParam));
        return true;
    }

    public boolean deleteRoutes(List<Integer> ids) {
        routeMapper.deleteRoutes(ids);
        return true;

    }


    public Long routePageCount() {
        return routeMapper.routeCount();
    }

    private boolean checkStationExist(List<Integer> ids) {
        return stationMapper.checkRouteStationsExist(ids);
    }


}
