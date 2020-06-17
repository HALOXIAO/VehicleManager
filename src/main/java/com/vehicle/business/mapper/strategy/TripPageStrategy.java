package com.vehicle.business.mapper.strategy;

import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.TripVO;
import org.hibernate.Session;

import java.util.List;

/**
 * @author HALOXIAO
 **/
public interface TripPageStrategy {

    List<TripVO> tripPage(TripPageParam tripPageParam, Session session);

}
