package com.vehicle.business.mapper.strategy;

import com.vehicle.business.module.param.TripPageParam;
import org.hibernate.Session;

/**
 * @author HALOXIAO
 **/
public interface TripPageCountStrategy {

    Long tripPageCount(TripPageParam tripPageParam, Session session);

}
