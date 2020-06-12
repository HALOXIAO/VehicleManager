package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

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


    public void updateDriverBatch(List<Driver> drivers) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        for (Driver driver : drivers) {
            session.update(driver);
        }
        transaction.commit();
        session.close();
    }

    public List<Driver> getDriverPage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        NativeQuery driverQuery = session.createSQLQuery("SELECT id,name,salary FROM bus_conf_driver LIMIT #{page},#{size}");
        driverQuery.addEntity(Driver.class);
        List<Driver> drivers = driverQuery.list();

        transaction.commit();
        session.close();
        return drivers;
    }

    public Long countDriver() {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        session.setDefaultReadOnly(true);
        NativeQuery driverQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_driver");
        transaction.commit();
        session.close();
        return driverQuery.list() == null ? null : driverQuery.list().size() == 1 ? (Long) driverQuery.list().get(0) : null;
    }


    public void deleteDriverBatch(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        for (Integer id : ids) {
            session.delete(id);
        }
        transaction.commit();
        session.close();
    }
}