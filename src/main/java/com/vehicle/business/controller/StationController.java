package com.vehicle.business.controller;

import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.StationNameParam;
import com.vehicle.business.module.param.StationUpdatedParam;
import com.vehicle.business.module.vo.StationTotalVO;
import com.vehicle.business.service.StationService;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Controller
public class StationController {

    @Autowired
    StationService stationService;


    @RequestMapping("/station")
    public ResultBean<StationTotalVO> getStationPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageParam pageParam = new PageParam();
        pageParam.setPage(page);
        pageParam.setSize(size);
        StationTotalVO stationTotalVO = new StationTotalVO();

        ResultBean<StationTotalVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(stationTotalVO);
        return bean;
    }

    @RequestMapping(value = "/station", method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> addStations(@RequestBody List<StationNameParam> names) {

        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

    }

    @RequestMapping(value = "/station", method = HTTP_METHOD.HTTP_PUT)
    public ResultBean<Boolean> updateStations(@RequestBody List<StationUpdatedParam> stationUpdatedParams) {

        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/station", method = HTTP_METHOD.HTTP_DELETE)
    public ResultBean<Boolean> deleteStations(@RequestBody List<Integer> ids) {

        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
