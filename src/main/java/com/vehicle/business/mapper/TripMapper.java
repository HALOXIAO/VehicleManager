package com.vehicle.business.mapper;

import com.sun.istack.NotNull;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.strategy.*;
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

    public void addTrip(Trip trip, @NotNull Session session) {
        session.save(trip);
    }

    public void updateTrip(Trip trip, @NotNull Session session) {
        session.update(trip);

    }

    public Long tripPageCount(@NotNull TripPageParam tripPageParam, @NotNull Session session) {
        assert tripPageParam != null;
        if (tripPageParam.getDate() != null && tripPageParam.getRoute() != null) {
            TripPageCountStrategy pageCount = new TripPageCountAllExistImpl();
            return pageCount.tripPageCount(tripPageParam, session);
        } else if (tripPageParam.getDate() == null && tripPageParam.getRoute() == null) {
            TripPageCountStrategy pageCount = new TripPageCountAllNonImpl();
            return pageCount.tripPageCount(tripPageParam, session);
        } else if (tripPageParam.getRoute() != null) {
            TripPageCountStrategy pageCount = new TripPageCountNonDateImpl();
            return pageCount.tripPageCount(tripPageParam, session);
        } else {
            TripPageCountStrategy pageCount = new TripPageCountNonRouteImpl();
            return pageCount.tripPageCount(tripPageParam, session);
        }

    }


}
