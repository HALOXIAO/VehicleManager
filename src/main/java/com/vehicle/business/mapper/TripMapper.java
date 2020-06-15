package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Repository
public class TripMapper {


    public List<Trip> getTripPage(TripPageParam tripPageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query query = session.createQuery("SELECT id,routeId,vehicleNumber FROM Trip ");
        return null;
    }

    public void addTrip(Trip trip, Session session) {
        boolean tag = false;
        if (session == null) {
            tag = true;
            session = HibernateUtilConfig.getSession();
        }
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(trip);
        transaction.commit();
        if (tag) {
            session.close();
        }
    }

}
