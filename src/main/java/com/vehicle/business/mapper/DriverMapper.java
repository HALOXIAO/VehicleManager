package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
        transaction.begin();
        Query driverQuery = session.createQuery("SELECT id,name,salary FROM Driver ");
        driverQuery.setMaxResults(pageParam.getSize());
        driverQuery.setFetchSize(pageParam.getPage());
        Object[] obj = driverQuery.list().toArray();
        transaction.commit();
        List<Driver> resultList = new ArrayList<>(driverQuery.list().size());
        session.close();
        for (Object object :obj){
            Object[] temp = (Object[])object;
            Driver driver = new Driver();
            driver.setId((Integer) temp[0]);
            driver.setName((String)temp[1]);
            driver.setSalary((BigDecimal) temp[2]);
            resultList.add(driver);
        }
        return resultList;
    }

    public Long countDriver() {
        Session session = HibernateUtilConfig.getSession();
        session.setDefaultReadOnly(true);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        NativeQuery driverQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_driver");
        transaction.commit();
        session.close();
        BigInteger count = driverQuery.list() == null ? null : driverQuery.list().size() == 1 ? (BigInteger) driverQuery.list().get(0) : null;
        return count.longValue();
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