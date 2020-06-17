package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.mapper.strategy.TripPageStrategy;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.RouteSimpleVO;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripPageAllNonImpl implements TripPageStrategy {


    @Override
    public List<TripVO> tripPage(TripPageParam tripPageParam, Session session) {
        NativeQuery query = session.createSQLQuery("SELECT p.id,p.vehicle_number,p.date,p.seats,r.id route_Id,r.name FROM " +
                "(SELECT id,vehicle_number,route_id,date,seats FROM bus_conf_trip WHERE id <= (SELECT id FROM bus_conf_trip WHERE status=?1 " +
                "ORDER BY id DESC LIMIT ?2,1) LIMIT ?3 ) p,bus_conf_route r WHERE p.route_id = r.id");
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        query.setParameter(2, tripPageParam.getPage());
        query.setParameter(3, tripPageParam.getSize());
        List<TripVO> tripVOList = new LinkedList<>();
        Object[] temp = query.list().toArray();
        for (Object object : temp) {
            Object[] obj = (Object[]) object;
            TripVO tripVO = new TripVO();
            tripVO.setId((Integer) obj[0]);
            tripVO.setVehicleNumber((String) obj[1]);
            tripVO.setDate((String) obj[2]);
            tripVO.setSeats((Integer) obj[3]);
            tripVO.setRoute(new RouteSimpleVO((Integer) obj[4], (String) obj[5]));
            tripVOList.add(tripVO);
        }
        return tripVOList;
    }
}
