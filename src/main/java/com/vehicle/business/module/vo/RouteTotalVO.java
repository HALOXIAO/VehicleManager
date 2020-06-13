package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class RouteTotalVO {

    List<RouteVO> routeVO;

    long total;
    }
