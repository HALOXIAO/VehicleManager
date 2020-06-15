package com.vehicle.business.module.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TripUpdatedParam {
    private Integer id;


    private String vehicleNumber;

    private Integer seats;

    private Integer routeId;


    private String date;

}
