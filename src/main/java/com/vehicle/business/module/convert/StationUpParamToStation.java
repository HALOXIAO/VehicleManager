package com.vehicle.business.module.convert;

import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.StationUpdatedParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface StationUpParamToStation {
    StationUpParamToStation INSTANCE = Mappers.getMapper(StationUpParamToStation.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address"),
            @Mapping(target = "status", ignore = true)
    }
    )
    Station toStation(StationUpdatedParam stationUpdatedParam);

    List<Station> toStationList(List<StationUpdatedParam> stationUpdatedParamList);

}
