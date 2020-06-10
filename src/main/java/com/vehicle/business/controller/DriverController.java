package com.vehicle.business.controller;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.convert.DriverParamToDriver;
import com.vehicle.business.module.param.DriverParam;
import com.vehicle.business.service.DriverService;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Controller
@Slf4j
public class DriverController {

    @Autowired
    public DriverService driverService;

    @RequestMapping(value = "/driver", method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> addDrivers(@RequestBody List<DriverParam> drivers) {
        DriverParam driverParam = drivers.get(0);
        log.info(driverParam.getName());
        log.info(driverParam.getSalary().toString());
        Driver driver = DriverParamToDriver.INSTANCE.toDriver(driverParam);
        log.info(driver.getName());
        log.info(driver.getSalary().toString());
        log.info("start to addDrivers");
        List<Driver> driverList = DriverParamToDriver.INSTANCE.toDriverList(drivers);
        driverService.addDrivers(driverList);
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }
}
