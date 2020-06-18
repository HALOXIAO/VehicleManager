package com.vehicle.business.module.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTotalVO {

    List<VehicleVO> vehicleVO;

    long total;

}
