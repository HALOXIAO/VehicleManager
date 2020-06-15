package com.vehicle.business.service;

import com.vehicle.business.mapper.DriverMapper;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.convert.DriverToDriverVo;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.DriverTotalVO;
import com.vehicle.business.module.vo.DriverVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class DriverService {

    @Autowired
    public DriverMapper driverMapper;


    public void addDrivers(List<Driver> drivers) {
        driverMapper.addDriverBatch(drivers);
    }

    public void updateDrivers(List<Driver> drivers) {
        driverMapper.updateDriverBatch(drivers);
    }

    public DriverTotalVO getDriversPage(PageParam pageParam) {
        pageParam.setPage(pageParam.getPage());
        List<Driver> drivers = driverMapper.getDriverPage(pageParam);
        List<DriverVO> driverVOList = DriverToDriverVo.INSTANCE.toDriverVOList(drivers);
        Long count = driverMapper.countDriver();
        return new DriverTotalVO(driverVOList, count);
    }

    public void deleteDrivers(List<Integer> ids) {
        driverMapper.deleteDriverBatch(ids);
    }

}
