package com.vehicle.business.mapper;

import com.alibaba.fastjson.JSON;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.convert.DriverToDriverVo;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.DriverTotalVO;
import com.vehicle.business.module.vo.DriverVO;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return  driverQuery.getResultList()==null ?null:(Long) driverQuery.getResultList().get(0);
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