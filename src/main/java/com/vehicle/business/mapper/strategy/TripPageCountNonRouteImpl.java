package com.vehicle.business.mapper.strategy;

import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author HALOXIAO
 **/
public class TripPageCountNonRouteImpl implements TripPageCountStrategy {

    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Long tripPageCount(TripPageParam tripPageParam, Session session) {
        String startTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(TIME_FORMATTER);
        String endTime = tripPageParam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1).format(TIME_FORMATTER);
        NativeQuery query = session.createSQLQuery("SELECT COUNT(*) FROM bus_conf_trip p , bus_conf_route r WHERE p.status= ?1 AND p.route_id=r.id AND p.date>?2 AND p.date<?3 ");
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(2, startTime);
        query.setParameter(3, endTime);
        BigInteger count = query.list() == null ? null : query.list().size() == 1 ? (BigInteger) query.list().get(0) : null;
        Objects.requireNonNull(count);
        return count.longValue();
    }
}
