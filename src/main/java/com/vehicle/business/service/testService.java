package com.vehicle.business.service;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.testM;
import com.vehicle.framework.core.annotation.Component;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author HALOXIAO
 **/
@Component
public class testService implements testServiceInter {

    @Override
    public testM get() {
        Session session = null;
        Transaction transaction = null;
        session = HibernateUtilConfig.getSession();
        session.get(testM.class,"HALO");
        transaction.commit();
        session.close();
        return null ;
    }
}
