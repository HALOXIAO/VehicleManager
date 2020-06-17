package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.common.util.DateTimeUtils;
import com.vehicle.business.mapper.strategy.TripPageStrategy;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.time.ZoneId;
import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripPageNonRouteImpl implements TripPageStrategy {

    @Override
    public List<TripVO> tripPage(TripPageParam tripPageParam, Session session) {

        String startTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeUtils.TIME_FORMATTER);
        String endTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1).format(DateTimeUtils.TIME_FORMATTER);
        NativeQuery query = session.createSQLQuery("SELECT p.id,p.vehicle_number,p.date,p.seats,r.id route_Id,r.name FROM  " +
                "(SELECT id,vehicle_number,route_id,date,seats FROM bus_conf_trip " +
                "WHERE id <= (SELECT id FROM bus_conf_trip WHERE status=?1 AND date>?2  AND date < ?3  " +
                "ORDER BY id DESC LIMIT  ?4,1)  AND date> ?5 AND date<?6  LIMIT ?7) p,bus_conf_route r WHERE p.route_id = r.id ");
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(2, startTime);
        return TripPageTransform.tripVOListTransform(query.list());
    }
}
