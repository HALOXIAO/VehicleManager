package com.vehicle.business.mapper.strategy.impl;

import com.vehicle.business.common.util.DateTimeUtils;
import com.vehicle.business.module.vo.RouteSimpleVO;
import com.vehicle.business.module.vo.TripVO;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripPageTransform {

    protected static List<TripVO> tripVOListTransform(List queryList) {
        Object[] temp = queryList.toArray();
        List<TripVO> tripVOList = new LinkedList<>();
        for (Object object : temp) {
            Object[] obj = (Object[]) object;
            TripVO tripVO = new TripVO();
            tripVO.setId((Integer) obj[0]);
            tripVO.setVehicleNumber((String) obj[1]);
            Timestamp date = (Timestamp) obj[2];
            tripVO.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeUtils.TIME_FORMATTER));
            tripVO.setSeats((Integer) obj[3]);
            tripVO.setRoute(new RouteSimpleVO((Integer) obj[4], (String) obj[5]));
            tripVOList.add(tripVO);
        }
        return tripVOList;
    }

}
