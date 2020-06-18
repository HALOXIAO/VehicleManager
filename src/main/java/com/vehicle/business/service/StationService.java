package com.vehicle.business.service;

import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
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
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
@Slf4j
public class StationService {

    @Autowired
    public StationMapper stationMapper;

    public StationTotalVO getStationPage(PageParam pageParam) {
        pageParam.setPage(pageParam.getPage() - 1);
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        List<Station> stationList = stationMapper.getStationPage(pageParam,session);
        Long count = stationMapper.countStation(session);
        SessionUtils.subsequentProcessing(session);
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
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.beginTransaction();
        stationList.forEach(station -> {
            stationMapper.updateStation(station, session);
        });
        try {
            SessionUtils.subsequentProcessing(session);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            transaction.rollback();
            throw e;
        }
        return true;
    }

    public boolean deleteStations(List<Integer> ids) {
        List<Station> stationList = new ArrayList<>(ids.size());
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction =  session.beginTransaction();
        stationList.forEach(station -> {
            stationMapper.updateStation(station, session);
        });
        try {
            SessionUtils.subsequentProcessing(session);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            transaction.rollback();
            throw e;
        }
        return true;
    }

}
