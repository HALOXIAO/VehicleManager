package com.vehicle.business.service;


import com.vehicle.business.common.exception.DataBaseRelationshipException;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.TripMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.param.TripParam;
import com.vehicle.business.module.param.TripUpdatedParam;
import com.vehicle.business.module.vo.TripTotalVO;
import com.vehicle.business.module.vo.TripVO;
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


    private boolean checkRoute(Integer id, Session session) {
        session.setDefaultReadOnly(true);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Route route = session.get(Route.class, id);
        transaction.commit();
        return route != null;
    }

    private boolean checkVehicle(String vehicleNumber, Session session) {
        session.setDefaultReadOnly(true);
        Transaction transaction = session.getTransaction();
        transaction.begin();
//        Query query = session.createQuery("select id FROM ")
//        Route route = session.get(Route.class, id);
        transaction.commit();
        return false;
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
            throw new IllegalArgumentException("日期错误");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("日期不应该比现在小");
        }
        Trip trip = new Trip();
        trip.setDate(date);
        trip.setVehicleNumber(tripParam.getVehicleNumber());
        trip.setSeats(tripParam.getSeats());
        trip.setRouteId(tripParam.getRouteId());

        return false;

    }

    public boolean updateTrip(TripUpdatedParam tripUpdatedParam) {
        if (tripUpdatedParam.getRouteId() != null) {
//            if (!checkRoute(tripUpdatedParam.getRouteId())) {
//                throw new DataBaseRelationshipException("route does not exist");
//            }
        }
        if (tripUpdatedParam.getVehicleNumber() != null) {
//            if (!checkVehicle(tripUpdatedParam.getVehicleNumber())) {
//                throw new DataBaseRelationshipException("vehicle does not exist");
//            }
        }
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(tripUpdatedParam.getDate(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("日期错误");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("日期不应该小于当前日期");
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
/*        TripTotalVO tripTotalVO = new TripTotalVO();
        tripTotalVO.setTotal(total);
        tripTotalVO.setTripVO(tripVOList);*/
        return null;
    }

    private Integer getTripPageCount(TripPageParam tripPageParam) {
        return 0;


    }

}
