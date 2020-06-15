package com.vehicle.business.module.convert;

import com.vehicle.business.module.Station;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.StationUpdatedParam;
import com.vehicle.business.module.param.TripParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author HALOXIAO
 **/
public class TripParamToTrip {

    public static Trip toTrip(TripParam tripParam, DateTimeFormatter dateTimeFormatter) {
        Trip trip = new Trip();
        trip.setRouteId(tripParam.getRouteId());
        trip.setSeats(tripParam.getSeats());
        trip.setVehicleNumber(tripParam.getVehicleNumber());
        trip.setStatus(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        trip.setDate(LocalDateTime.parse(tripParam.getDate(), dateTimeFormatter));
        return trip;
    }

}
