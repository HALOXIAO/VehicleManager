package com.vehicle.business.controller;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.convert.DriverParamToDriver;
import com.vehicle.business.module.convert.DriverUpParamToDriver;
import com.vehicle.business.module.param.DriverParam;
import com.vehicle.business.module.param.DriverUpdatedParam;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.DriverTotalVO;
import com.vehicle.business.service.DriverService;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;
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
        List<Driver> driverList = DriverParamToDriver.INSTANCE.toDriverList(drivers);
        driverService.addDrivers(driverList);
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/driver", method = HTTP_METHOD.HTTP_PUT)
    public ResultBean<Boolean> updateDriver(@RequestBody List<DriverUpdatedParam> drivers) {
        List<Driver> driverList = DriverUpParamToDriver.INSTANCE.toDriverList(drivers);
        driverService.updateDrivers(driverList);
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/driver")
    public ResultBean<DriverTotalVO> searchDrivers(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageParam pageParam = new PageParam();
        pageParam.setPage(page);
        pageParam.setSize(size);
        DriverTotalVO result = driverService.getDriversPage(pageParam);
        ResultBean<DriverTotalVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(result);
        return bean;
    }

    @RequestMapping(value = "/drivers", method = HTTP_METHOD.HTTP_DELETE)
    public ResultBean<Boolean> deleteDrivers(@RequestBody List<Integer> ids) {
        driverService.deleteDrivers(ids);
        return new ResultBean<Boolean>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }


}
