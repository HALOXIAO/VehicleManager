package com.vehicle.business.controller;

import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.RouteParam;
import com.vehicle.business.module.param.RouteUpdatedParam;
import com.vehicle.business.module.vo.RouteTotalVO;
import com.vehicle.business.module.vo.RouteVO;
import com.vehicle.business.service.RouteService;
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
public class RouteController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/route",method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> addRoute(@RequestBody RouteParam routeParam) {
        if (routeService.addRoute(routeParam).getId() != null) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }


    @RequestMapping(value = "/route",method = HTTP_METHOD.HTTP_PUT)
    public ResultBean<Boolean> updateRoute(@RequestBody RouteUpdatedParam routeUpdatedParam) {
        if (!routeService.updateRoute(routeUpdatedParam)) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/route",method = HTTP_METHOD.HTTP_DELETE)
    public ResultBean<Boolean> deleteRoute(@RequestBody List<Integer> ids) {
        if (!ArgumentCheck.checkIds(ids)) {
            return new ResultBean<>("argument", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        if (!routeService.deleteRoutes(ids)) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        }
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @RequestMapping(value = "/route")
    public ResultBean<RouteTotalVO> getRoutes(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageParam pageParam = new PageParam();
        pageParam.setPage(page);
        pageParam.setSize(size);
        RouteTotalVO routeTotalVO = new RouteTotalVO();
        routeTotalVO.setRouteVO(routeService.getRoutes(pageParam));
        routeTotalVO.setTotal(routeService.routePageCount());
        ResultBean<RouteTotalVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(routeTotalVO);
        return bean;
    }

    @GetMapping("/route/detail")
    public ResultBean<RouteVO> getRoute(@RequestParam("id")  Integer id) {
        RouteVO routeVO = routeService.getRoute(id);
        ResultBean<RouteVO> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(routeVO);
        return bean;
    }

}
