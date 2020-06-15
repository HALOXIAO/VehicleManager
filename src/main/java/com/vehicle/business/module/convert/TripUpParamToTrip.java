package com.vehicle.business.module.convert;

import com.vehicle.business.module.Station;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.StationUpdatedParam;
import com.vehicle.business.module.param.TripUpdatedParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface TripUpParamToTrip {

    TripUpParamToTrip INSTANCE = Mappers.getMapper(TripUpParamToTrip.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "vehicleNumber",target = "vehicleNumber"),
            @Mapping(source = "seats",target = "seats"),
            @Mapping(source = "routeId",target = "routeId"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "date",ignore = true)
    }
    )
    Trip toTrip(TripUpdatedParam tripUpdatedParam);

    List<Trip> toTripList(List<TripUpdatedParam> tripUpdatedParamList);


}
