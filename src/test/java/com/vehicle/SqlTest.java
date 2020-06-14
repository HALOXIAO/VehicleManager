package com.vehicle;

import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.service.StationService;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Component;
import org.junit.Test;

/**
 * @author HALOXIAO
 **/
@Component
public class SqlTest {

    @Autowired
    StationService stationService;


    @Test
    public void contextLoader() {
        PageParam pageParam = new PageParam(1,10);
        stationService.getStationPage(pageParam);
    }
}
