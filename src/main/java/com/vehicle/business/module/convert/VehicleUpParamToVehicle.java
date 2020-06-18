package com.vehicle.business.module.convert;

import com.vehicle.business.module.Vehicle;
import com.vehicle.business.module.param.VehicleUpdatedParam;
import com.vehicle.business.module.vo.VehicleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-06-18 17:50
 **/
@Mapper
public interface VehicleUpParamToVehicle {

    VehicleUpParamToVehicle INSTANCE = Mappers.getMapper(VehicleUpParamToVehicle.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "vehicleNumber", target = "vehicleNumber"),
            @Mapping(source = "vehicleModel", target = "vehicleModel"),
            @Mapping(source = "seatCount", target = "seatCount")
    })
    Vehicle toVehicle(VehicleUpdatedParam vehicleUpdatedParam);

    List<Vehicle> toVehicleList(List<VehicleUpdatedParam> vehicleUpdatedParamList);

}
