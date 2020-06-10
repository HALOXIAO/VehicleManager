package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Repository
public class DriverMapper {

    public void addDriverBatch(List<Driver> drivers) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        for (Driver driver : drivers) {
            session.save(driver);
        }
        transaction.commit();
        session.close();
    }


}
