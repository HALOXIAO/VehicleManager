package com.vehicle.business.module.convert;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.DriverUpdatedParam;
import com.vehicle.business.module.param.StationNameParam;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface StationNameParamToStation {
    StationNameParamToStation INSTANCE = Mappers.getMapper(StationNameParamToStation.class);

    List<Station> toDriverList(List<StationNameParam> stationNameParams);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status",ignore = true)
    })
    Station toDriver(StationNameParam driverParam);


}
