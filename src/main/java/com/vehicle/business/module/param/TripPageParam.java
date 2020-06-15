package com.vehicle.business.module.param;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TripPageParam extends PageParam {

    private Integer route;

    private Date date;


}
