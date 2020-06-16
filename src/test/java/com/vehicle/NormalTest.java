package com.vehicle;

import com.vehicle.business.config.HibernateUtilConfig;
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
import java.util.*;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT COUNT(*) FROM bus_conf_trip p,bus_conf_route r WHERE p.status=1 AND p.route_id=r.id");
//        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        BigInteger count = nativeQuery.list() == null ? null : nativeQuery.list().size() == 1 ? (BigInteger) nativeQuery.list().get(0) : null;
        transaction.commit();
        session.close();
        Objects.requireNonNull(count);
        System.out.println(count);

/*        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(*) FROM Trip p,Route r WHERE p.status=1 AND p.routeId=r.id");
//        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        List list = query.list();
        transaction.commit();
        Long count = query.list() == null ? null : query.list().size() == 1 ? (Long) query.list().get(0) : null;
        System.out.println(count);
        session.close();*/
    }

}
