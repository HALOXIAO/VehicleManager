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
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
/*        Trip trip = new Trip();

        trip.setId(28);
        trip.setStatus(DATABASE_COMMON_STATUS_CODE.DELETE.getValue());
        trip.setDate(LocalDateTime.now());
        trip.setRouteId(1);
        trip.setVehicleNumber("ADAWD");
        trip.setSeats(213);
        session.update(trip);*/
        Driver d = new Driver();
        Query query = session.createQuery("UPDATE  Driver   SET name=?1 WHERE id =47");
        query.setParameter(1, "testasacq123");
        int row = query.executeUpdate();
        System.out.println(row);
        transaction.commit();
        session.close();

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
