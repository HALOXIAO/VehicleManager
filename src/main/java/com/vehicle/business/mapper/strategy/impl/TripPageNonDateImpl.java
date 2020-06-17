package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.mapper.strategy.TripPageStrategy;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripPageNonDateImpl implements TripPageStrategy {

    @Override
    public List<TripVO> tripPage(TripPageParam tripPageParam, Session session) {
        NativeQuery query = session.createSQLQuery("SELECT  p.id,p.vehicle_number,p.date,p.seats,r.id " +
                "route_Id,r.name FROM (SELECT id,vehicle_number,route_id,date,seats FROM bus_conf_trip " +
                "WHERE route_id=?1 AND  id <= (SELECT id FROM bus_conf_trip WHERE status= ?2  AND route_id= ?3  " +
                " ORDER BY id DESC LIMIT ?4,1) LIMIT ?5 ) p,bus_conf_route r WHERE p.route_id = r.id ");
        query.setParameter(1, tripPageParam.getRoute());
        query.setParameter(2, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(3, tripPageParam.getRoute());
        query.setParameter(4, tripPageParam.getPage());
        query.setParameter(5, tripPageParam.getSize());
        return  TripPageTransform.tripVOListTransform(query.list());
    }
}
