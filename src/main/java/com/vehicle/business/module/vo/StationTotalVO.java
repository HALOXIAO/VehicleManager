package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Setter
@Getter
public class StationTotalVO {

    List<StationVO> stationVO;

    long total;

}
