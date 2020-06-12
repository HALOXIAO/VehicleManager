package com.vehicle.business.service;

import com.vehicle.business.mapper.StationMapper;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.StationTotalVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

/**
 * @author HALOXIAO
 **/
@Service
public class StationService {

    @Autowired
    StationMapper stationMapper;

    public StationTotalVO getStationPage(PageParam pageParam) {
        return null;
    }

}
