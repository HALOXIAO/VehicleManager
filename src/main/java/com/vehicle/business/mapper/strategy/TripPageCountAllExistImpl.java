package com.vehicle.business.mapper.strategy;

import com.vehicle.business.module.param.TripPageParam;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

/**
 * @author HALOXIAO
 **/
public class TripPageCountAllExistImpl implements TripPageCountStrategy {
    /*
     *         return new StringBuilder("bus_conf_trip p , bus_conf_route r WHERE p.status=").
     * append(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue()).append(" AND p.route_id=r.id \0")
     * .append("\0 AND p.route_id= #{tripPageParam.route} \0").append("\0 AND p.date>")
     * .append(startTime).append("\0 AND p.date<").append(endTime).toString();

     *
     * */
    @Override
    public Long tripPageCount(TripPageParam tripPageParam, Session session) {

        NativeQuery query = session.createSQLQuery("SELECT COUNT(*) FROM bus_conf_trip p , bus_conf_route r WHERE p.status=?1 " +
                "AND p.route_id=r.id AND p.route_id=?2 AND date>?3 AND date <?4");



        return null;
    }
}



