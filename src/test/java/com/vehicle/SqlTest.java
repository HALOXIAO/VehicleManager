package com.vehicle;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Route;
import com.vehicle.framework.core.annotation.Component;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author HALOXIAO
 **/
@Component
public class SqlTest {


    @Test
    public void contextLoader() {
        Session session = HibernateUtilConfig.getSession();
        Session session2 = HibernateUtilConfig.getSession();
        System.out.println(session.equals(session2));
    }
}
