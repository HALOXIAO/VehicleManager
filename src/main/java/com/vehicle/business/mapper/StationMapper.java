package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.StationVO;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Repository
public class StationMapper {

    public List<StationVO> getStationPage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        NativeQuery driverQuery = session.createSQLQuery("SELECT id, name, address FROM bus_conf_station WHERE status={} LIMIT #{page},#{size} ");
        driverQuery.addEntity(Driver.class);
        List<Driver> drivers = driverQuery.list();

        transaction.commit();
        session.close();
        return drivers;
    }


}
