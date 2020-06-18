package com.vehicle.business.module.convert;

import com.vehicle.business.module.Vehicle;
import com.vehicle.business.module.param.VehicleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-06-18 18:11
 **/
@Mapper
public interface VehicleParamToVehicle {

    VehicleParamToVehicle INSTANCE = Mappers.getMapper(VehicleParamToVehicle.class);

    @Mappings({
            @Mapping(source = "vehicleNumber", target = "vehicleNumber"),
            @Mapping(source = "vehicleModel", target = "vehicleModel"),
            @Mapping(source = "seatCount", target = "seatCount"),
            @Mapping(target = "id", ignore = true)
    })
    Vehicle toVehicle(VehicleParam vehicleParam);

    List<Vehicle> toVehicleList(List<VehicleParam> vehicleParamList);


}
