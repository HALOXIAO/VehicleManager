package com.vehicle.business.service;

import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.convert.StationNameParamToStation;
import com.vehicle.business.module.convert.StationToStationVO;
import com.vehicle.business.module.convert.StationUpParamToStation;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.StationNameParam;
import com.vehicle.business.module.param.StationUpdatedParam;
import com.vehicle.business.module.vo.StationTotalVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class StationService {

    @Autowired
    public StationMapper stationMapper;

    public StationTotalVO getStationPage(PageParam pageParam) {
        pageParam.setPage(pageParam.getPage() - 1);
        List<Station> stationList = stationMapper.getStationPage(pageParam);
        Long count = stationMapper.countStation();
        return new StationTotalVO(StationToStationVO.INSTANCE.toStationVOList(stationList), count);
    }

    public boolean addStations(List<StationNameParam> stationParam) {
        List<Station> stationList = StationNameParamToStation.INSTANCE.toDriverList(stationParam);
        stationList.forEach(p -> {
            p.setStatus(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        });
        stationMapper.addStations(stationList);
        return true;
    }

    public boolean updateStations(List<StationUpdatedParam> stationUpdatedParams) {
        List<Station> stationList = StationUpParamToStation.INSTANCE.toStationList(stationUpdatedParams);
        stationMapper.updateStation(stationList);
        return true;
    }

    public boolean deleteStations(List<Integer> ids) {
        List<Station>stationList = new ArrayList<>(ids.size());
        ids.forEach(id->{
            Station station = new Station();
            station.setStatus(DATABASE_COMMON_STATUS_CODE.DELETE.getValue());
            stationList.add(station);
        });
        stationMapper.updateStation(stationList);
        return true;
    }

}
