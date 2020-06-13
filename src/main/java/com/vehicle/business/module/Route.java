package com.vehicle.business.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {

    private Integer id;

    private Integer startId;
    private Integer endId;
    private String detail;
    private String name;

    private Integer status;
}
