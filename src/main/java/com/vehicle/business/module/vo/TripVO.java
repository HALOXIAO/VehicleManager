package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TripVO {


    private Integer id;

    private String vehicleNumber;

    private RouteSimpleVO route;

    private String date;

    private Integer seats;

}
