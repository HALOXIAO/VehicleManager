package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class RouteVO {


    private List<StationVO>routeDetail;
    private String name;
    private Integer id;

}
