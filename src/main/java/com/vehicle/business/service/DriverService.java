package com.vehicle.business.service;

import com.vehicle.business.mapper.DriverMapper;
import com.vehicle.business.module.Driver;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class DriverService {

    @Autowired
    public DriverMapper driverMapper;


    public void addDrivers(List<Driver> drivers) {
        driverMapper.addDriverBatch(drivers);
    }

}
