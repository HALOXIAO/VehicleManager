package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.mapper.strategy.TripPageCountStrategy;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author HALOXIAO
 **/
public class TripPageCountNonDateImpl implements TripPageCountStrategy {


    @Override
    public Long tripPageCount(TripPageParam tripPageParam, Session session) {
        NativeQuery query = session.createSQLQuery("SELECT COUNT(*) FROM bus_conf_trip p , bus_conf_route r WHERE p.status= ?1 AND p.route_id=r.id AND p.route_id=?2 ");
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(2, tripPageParam.getRoute());
        BigInteger count = query.list() == null ? null : query.list().size() == 1 ? (BigInteger) query.list().get(0) : null;
        Objects.requireNonNull(count);
        return count.longValue();
    }
}
