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
    public StationMapper stationMapper;

    public StationTotalVO getStationPage(PageParam pageParam) {
        pageParam.setPage(pageParam.getPage() - 1);
        List<Station> stationList = stationMapper.getStationPage(pageParam);
        Long count = stationMapper.countStation();
        return new StationTotalVO(StationToStationVO.INSTANCE.toStationVOList(stationList), count);
    }

}
