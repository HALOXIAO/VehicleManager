package com.vehicle.business.service;

import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.convert.StationToStationVO;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.StationTotalVO;
import com.vehicle.business.module.vo.StationVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class StationService {

    @Autowired
    StationMapper stationMapper;

    public StationTotalVO getStationPage(PageParam pageParam) {
        List<Station> stationVOList = stationMapper.getStationPage(pageParam);
        Long count = stationMapper.countStation();
        return new StationTotalVO(StationToStationVO.INSTANCE.toStationVOList(stationVOList), count);
    }

}