package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Setter
@Getter
public class VehicleTotalVO {

    List<VehicleVO> vehicleVO;

    long total;

}
