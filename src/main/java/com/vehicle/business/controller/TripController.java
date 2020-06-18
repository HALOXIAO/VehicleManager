package com.vehicle.business.controller;



import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.param.TripParam;
import com.vehicle.business.module.param.TripUpdatedParam;
import com.vehicle.business.module.vo.TripTotalVO;
import com.vehicle.business.service.TripService;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author HALOXIAO
 **/
@Controller
public class TripController {

    @Autowired
    public TripService tripService;

    @RequestMapping(value = "/trip",method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> addTrip(@RequestBody TripParam tripParam) {
        if (!tripService.addTrip(tripParam)) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/trip",method = HTTP_METHOD.HTTP_PUT)
    public ResultBean<Boolean> updateTrip(@RequestBody  TripUpdatedParam tripUpdatedParam) {
        if (!tripService.updateTrip(tripUpdatedParam)) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }


    @RequestMapping("/trip")
    public ResultBean<TripTotalVO> getTripPage(@RequestParam(value = "route",require = false) Integer route, @RequestParam(value = "date",require = false) String date, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Date tempDate = null;
        if (date != null) {
            tempDate = Date.from(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
        }
        TripPageParam tripPageParam = new TripPageParam();
        tripPageParam.setDate(tempDate);
        tripPageParam.setPage(page);
        tripPageParam.setRoute(route);
        tripPageParam.setSize(size);
        TripTotalVO tripTotalVO = tripService.getTripPage(tripPageParam);
        ResultBean<TripTotalVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(tripTotalVO);
        return bean;
    }
}

