package com.vehicle.business.module.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverTotalVO {

    List<DriverVO> driverVO;

    long total;


}
