package com.vehicle.business.module;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class Trip {

    private Integer id;


    private String vehicleNumber;

    private Integer routeId;

    private LocalDateTime date;

    private Integer seats;

    private Integer status;
}
