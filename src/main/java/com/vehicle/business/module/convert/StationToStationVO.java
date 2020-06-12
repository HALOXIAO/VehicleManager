package com.vehicle.business.module.convert;

import com.vehicle.business.module.Driver;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.DriverParam;
import com.vehicle.business.module.vo.StationVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Mapper
public interface StationToStationVO {

    StationToStationVO INSTANCE = Mappers.getMapper(StationToStationVO.class);

    List<StationVO> toStationVOList(List<Station> stations);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "id", target = "id")
    })
    StationVO toStationVO(Station station);
}
