package com.vehicle.business.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.VehicleMapper;
import com.vehicle.business.module.Vehicle;
import com.vehicle.business.module.convert.VehicleParamToVehicle;
import com.vehicle.business.module.convert.VehicleToVehicleVO;
import com.vehicle.business.module.convert.VehicleUpParamToVehicle;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.VehicleParam;
import com.vehicle.business.module.param.VehicleUpdatedParam;
import com.vehicle.business.module.vo.VehicleTotalVO;
import com.vehicle.business.module.vo.VehicleVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
@Slf4j
public class VehicleService {


    @Autowired
    public VehicleMapper vehicleMapper;


    public boolean addVehicle(VehicleParam vehicleParam) {
        Vehicle vehicle = VehicleParamToVehicle.INSTANCE.toVehicle(vehicleParam);
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        vehicleMapper.addVehicle(vehicle, session);
        SessionUtils.subsequentProcessing(session);
        return true;
    }


    public boolean deleteVehicle(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        vehicleMapper.deleteVehicles(ids, session);
        SessionUtils.subsequentProcessing(session);
        return true;
    }


    public boolean updateVehicle(VehicleUpdatedParam vehicleUpdatedParam) {
        Vehicle vehicle = VehicleUpParamToVehicle.INSTANCE.toVehicle(vehicleUpdatedParam);
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        boolean flag = vehicleMapper.updateVehicle(vehicle, session);
        SessionUtils.subsequentProcessing(session);
        return flag;
    }

    public VehicleTotalVO getVehiclePage(PageParam pageParam) {
        pageParam.setPage(pageParam.getPage() * pageParam.getSize() - 1);
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        List<Vehicle> vehicleList = vehicleMapper.getVehiclePage(pageParam, session);
        long total = vehicleMapper.getVehiclePageCount(session);
        SessionUtils.subsequentProcessing(session);
        List<VehicleVO> vehicleVOList = VehicleToVehicleVO.INSTANCE.toVehicleVOList(vehicleList);
        VehicleTotalVO vehicleTotalVO = new VehicleTotalVO();
        vehicleTotalVO.setTotal(total);
        vehicleTotalVO.setVehicleVO(vehicleVOList);
        return vehicleTotalVO;
    }
}

