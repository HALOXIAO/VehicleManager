package com.vehicle.business.module.convert;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.DriverUpdatedParam;
import com.vehicle.business.module.vo.DriverVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface DriverToDriverVo {
    DriverToDriverVo INSTANCE = Mappers.getMapper(DriverToDriverVo.class);

    List<DriverVO> toDriverVOList(List<Driver> drivers);


    @Mappings({
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "salary",target = "salary"),
            @Mapping(source = "id",target = "id")
    })
    DriverVO toDriverVO(Driver driver);



}
