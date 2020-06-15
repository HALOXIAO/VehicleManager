package com.vehicle.business.service;

import com.vehicle.business.mapper.VehicleMapper;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class VehicleService {


    @Autowired
    public VehicleMapper vehicleMapper;


    public boolean deleteVehicles(List<Integer> ids) {
        vehicleMapper.deleteVehicles(ids);
        return false;
    }

}
