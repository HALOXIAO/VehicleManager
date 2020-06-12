package com.vehicle.business.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author HALOXIAO
 **/
@Setter
@Getter
public class DriverVO {

    private Integer id;

    private String name;

    private BigDecimal salary;
}
