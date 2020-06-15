package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TripTotalVO {

    private List<TripVO> tripVO;

    private long total;

}
