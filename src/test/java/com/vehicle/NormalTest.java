package com.vehicle;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.Trip;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public void main()  {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id,routeId,vehicleNumber,date,seats FROM Trip WHERE status=?1");
        query.setMaxResults(10);
        query.setFirstResult(0);
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        Object[] objects = query.list().toArray();
        for (Object obj : objects) {
            Trip trip = (Trip)obj;
            System.out.println(trip);
        }
        transaction.commit();
        session.close();

    }

}
