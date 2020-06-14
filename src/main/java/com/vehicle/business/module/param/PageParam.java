package com.vehicle.business.module.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author HALOXIAO
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {



    Integer page = 1;



    Integer size = 10;

}
