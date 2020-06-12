package com.vehicle.business.module.convert;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.DriverUpdatedParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface DriverUpParamToDriver {
    DriverUpParamToDriver INSTANCE = Mappers.getMapper(DriverUpParamToDriver.class);

    List<Driver> toDriverList(List<DriverUpdatedParam> drivers);


    @Mappings({
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "salary",target = "salary"),
            @Mapping(source = "id",target = "id")
    })
    Driver toDriver(DriverUpdatedParam driverParam);


}
