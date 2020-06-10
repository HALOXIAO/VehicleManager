package com.vehicle.business.module.convert;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.DriverParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface DriverParamToDriver {

    DriverParamToDriver INSTANCE = Mappers.getMapper(DriverParamToDriver.class);

    List<Driver> toDriverList(List<DriverParam> drivers);


    @Mappings({
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "salary",target = "salary"),
            @Mapping(target = "id",ignore = true)
    })
    Driver toDriver(DriverParam driverParam);

}
