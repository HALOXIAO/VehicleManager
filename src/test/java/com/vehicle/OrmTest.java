package com.vehicle;

import com.vehicle.business.module.testM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @author HALOXIAO
 **/
public class OrmTest {

    private static SessionFactory factory;

    static {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }


    @Test
    public void main() {
        Session session = getSession();
        testM t = new testM();
        t.setName("asdwqesac");
        t.setPassword("qweqwe");
        t.setPermission("root");
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(t);
       transaction.commit();
        session.close();

    }

}
