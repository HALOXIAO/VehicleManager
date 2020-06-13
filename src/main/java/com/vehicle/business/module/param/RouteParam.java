package com.vehicle.business.module.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Setter
@Getter
public class RouteParam {


    private String name;


    private List<Integer> stationIds;
}
