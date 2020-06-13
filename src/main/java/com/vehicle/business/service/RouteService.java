package com.vehicle.business.service;

import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

/**
 * @author HALOXIAO
 **/
@Service
public class RouteService {

    @Autowired
    RouteMapper routeMapper;

}
