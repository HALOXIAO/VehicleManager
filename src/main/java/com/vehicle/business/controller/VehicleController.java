package com.vehicle.business.controller;


import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.VehicleParam;
import com.vehicle.business.module.param.VehicleUpdatedParam;
import com.vehicle.business.module.vo.VehicleTotalVO;
import com.vehicle.business.service.VehicleService;
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
public class VehicleController {


    @Autowired
    public VehicleService vehicleService;

    @RequestMapping(value = "/manage/vehicle", method = HTTP_METHOD.HTTP_DELETE)
    public ResultBean<Boolean> deleteVehicle(@RequestBody List<Integer> ids) {
        vehicleService.deleteVehicle(ids);
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping("/manage/vehicle")
    public ResultBean<VehicleTotalVO> getVehiclePage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageParam pageParam = new PageParam(page, size);
        VehicleTotalVO result = vehicleService.getVehiclePage(pageParam);
        ResultBean<VehicleTotalVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(result);
        return bean;
    }

    @RequestMapping(value = "/manage/vehicle", method = HTTP_METHOD.HTTP_PUT)
    public ResultBean<Boolean> updateVehicle(@RequestBody VehicleUpdatedParam vehicleUpdatedParam) {
        if (!vehicleService.updateVehicle(vehicleUpdatedParam)) {
            return new ResultBean<>("id not exist ", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/manage/vehicle", method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> addVehicle(@RequestBody VehicleParam vehicleParam) {
        vehicleService.addVehicle(vehicleParam);
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }


}

