package com.vehicle.business.module.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/

@Getter
@Setter
public class RouteUpdatedParam {


    private Integer id;


    private String name;


    private List<Integer> detail;


}
