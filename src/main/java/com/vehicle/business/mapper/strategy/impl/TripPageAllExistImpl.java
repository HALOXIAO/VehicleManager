package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.common.util.DateTimeUtils;
import com.vehicle.business.mapper.strategy.TripPageStrategy;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.RouteSimpleVO;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripPageAllExistImpl implements TripPageStrategy {

    @Override
    public List<TripVO> tripPage(TripPageParam tripPageParam, Session session) {
        String startTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeUtils.TIME_FORMATTER);
        String endTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1).format(DateTimeUtils.TIME_FORMATTER);
        NativeQuery query = session.createSQLQuery("SELECT p.id,p.vehicle_number,p.date,p.seats,r.id route_Id,r.name FROM " +
                "(SELECT id,vehicle_number,route_id,date,seats FROM bus_conf_trip WHERE route_id= ?1 AND " +
                " id <= (SELECT id FROM bus_conf_trip WHERE status=?2 " +
                " AND route_id= ?3 AND date>?4 AND date<?5 ORDER BY id DESC LIMIT ?6,1) LIMIT ?7 ) p,bus_conf_route r WHERE p.route_id = r.id");
        query.setParameter(1, tripPageParam.getRoute());
        query.setParameter(2, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(3, tripPageParam.getRoute());
        query.setParameter(4, startTime);
        query.setParameter(5, endTime);
        query.setParameter(6, tripPageParam.getPage());
        query.setParameter(7, tripPageParam.getSize());
        return TripPageTransform.tripVOListTransform(query.list());
    }
}
