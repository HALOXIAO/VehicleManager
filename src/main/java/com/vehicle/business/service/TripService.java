package com.vehicle.business.service;


import com.sun.istack.NotNull;
import com.vehicle.business.common.exception.DataBaseRelationshipException;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.TripMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.convert.TripParamToTrip;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.param.TripParam;
import com.vehicle.business.module.param.TripUpdatedParam;
import com.vehicle.business.module.vo.TripTotalVO;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Service
public class TripService {

    @Autowired
    public TripMapper tripMapper;

    @Autowired
    public RouteMapper routeMapper;

    private final String DB_TRIP_COLUMN_ID = "id";
    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private boolean checkRoute(@NotNull Integer id, Session session) {
        session.setDefaultReadOnly(true);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Route route = session.get(Route.class, id);
        transaction.commit();
        return route != null;
    }

    private boolean checkVehicle(@NotNull String vehicleNumber, Session session) {
        session.setDefaultReadOnly(true);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query query = session.createQuery("select id FROM Vehicle WHERE vehicleNumber=?1");
        query.setParameter(1, vehicleNumber);
        transaction.commit();
        return query.list() != null;
    }


    public boolean addTrip(TripParam tripParam) throws IllegalArgumentException {
        Session session = HibernateUtilConfig.getSession();
        if (!checkRoute(tripParam.getRouteId(), session) || !checkVehicle(tripParam.getVehicleNumber(), session)) {
            throw new DataBaseRelationshipException("route or vehicle does not exist");
        }
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(tripParam.getDate(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("date format wrong");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("date should be bigger than now ");
        }
        Trip trip = TripParamToTrip.toTrip(tripParam, TIME_FORMATTER);
        tripMapper.addTrip(trip, session);
        session.close();
        return true;

    }

    public boolean updateTrip(TripUpdatedParam tripUpdatedParam) {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(tripUpdatedParam.getDate(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("date format wrong");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("date should be bigger than now ");
        }
        Session session = HibernateUtilConfig.getSession();
        if (tripUpdatedParam.getRouteId() != null) {
            if (!checkRoute(tripUpdatedParam.getRouteId(), session)) {
                throw new DataBaseRelationshipException("route does not exist");
            }
        }
        if (tripUpdatedParam.getVehicleNumber() != null) {
            if (!checkVehicle(tripUpdatedParam.getVehicleNumber(),session)) {
                throw new DataBaseRelationshipException("vehicle does not exist");
            }
        }

        Trip trip = new Trip();
        trip.setDate(date);
        trip.setRouteId(tripUpdatedParam.getRouteId());
        trip.setSeats(tripUpdatedParam.getSeats());
        trip.setVehicleNumber(tripUpdatedParam.getVehicleNumber());
//        return tripMapper.update(trip, new UpdateWrapper<Trip>().eq(DB_TRIP_COLUMN_ID, tripUpdatedParam.getId()));
        return false;
    }

    public boolean deleteTrip(List<Integer> ids) {
        return false;
    }

    public TripTotalVO getTripPage(TripPageParam tripPageParam) {
        tripPageParam.setPage(((tripPageParam.getPage() - 1) * tripPageParam.getSize()));
        List<Trip> tripVOList = tripMapper.getTripPage(tripPageParam);

        int total = getTripPageCount(tripPageParam);
        TripTotalVO tripTotalVO = new TripTotalVO();
        tripTotalVO.setTotal(total);
//        tripTotalVO.setTripVO(tripVOList);
        return null;
    }

    private Integer getTripPageCount(TripPageParam tripPageParam) {
        return 0;


    }

}
