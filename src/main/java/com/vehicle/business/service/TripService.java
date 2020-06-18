package com.vehicle.business.service;


import com.sun.istack.NotNull;
import com.vehicle.business.common.exception.DataBaseRelationshipException;
import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.RouteMapper;
import com.vehicle.business.mapper.TripMapper;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.convert.TripParamToTrip;
import com.vehicle.business.module.convert.TripUpParamToTrip;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.param.TripParam;
import com.vehicle.business.module.param.TripUpdatedParam;
import com.vehicle.business.module.vo.TripTotalVO;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Slf4j
@Service
public class TripService {

    @Autowired
    public TripMapper tripMapper;

    @Autowired
    public RouteMapper routeMapper;

    private final String DB_TRIP_COLUMN_ID = "id";
    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private boolean checkRoute(@NotNull Integer id, Session session) {
        Route route = session.get(Route.class, id);
        return route != null;
    }

    private boolean checkVehicle(@NotNull String vehicleNumber, Session session) {
        Query query = session.createQuery("select id FROM Vehicle WHERE vehicleNumber=?1");
        query.setParameter(1, vehicleNumber);
        return query.list() != null;
    }


    public boolean addTrip(TripParam tripParam) throws IllegalArgumentException {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
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
        transaction.commit();
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
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        if (tripUpdatedParam.getRouteId() != null) {
            if (!checkRoute(tripUpdatedParam.getRouteId(), session)) {
                SessionUtils.subsequentProcessing(session);
                throw new DataBaseRelationshipException("route does not exist");
            }
        }
        if (tripUpdatedParam.getVehicleNumber() != null) {
            if (!checkVehicle(tripUpdatedParam.getVehicleNumber(), session)) {
                SessionUtils.subsequentProcessing(session);
                throw new DataBaseRelationshipException("vehicle does not exist");
            }
        }
        Trip trip = TripUpParamToTrip.INSTANCE.toTrip(tripUpdatedParam);
        trip.setDate(LocalDateTime.parse(tripUpdatedParam.getDate(), TIME_FORMATTER));
        trip.setStatus(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        boolean flag = tripMapper.updateTrip(trip, session);
        SessionUtils.subsequentProcessing(session);
        return flag;
    }

    public boolean deleteTrip(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        ids.forEach(id -> {
            Trip trip = new Trip();
            trip.setId(id);
            trip.setStatus(DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
            tripMapper.updateTrip(trip, session);
        });
        try {
            SessionUtils.subsequentProcessing(session);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            session.getTransaction().rollback();
            throw e;
        }
        return true;
    }

    public TripTotalVO getTripPage(TripPageParam tripPageParam) {
        tripPageParam.setPage((tripPageParam.getPage() * tripPageParam.getSize() - 1));
        Session session = HibernateUtilConfig.getSession();
        session.setDefaultReadOnly(true);
        session.beginTransaction();
        List<TripVO> tripList = tripMapper.getTripPage(tripPageParam, session);
        long total = getTripPageCount(tripPageParam, session);
        SessionUtils.subsequentProcessing(session);
        TripTotalVO tripTotalVO = new TripTotalVO();
        tripTotalVO.setTotal(total);
        tripTotalVO.setTripVO(tripList);
        return tripTotalVO;
    }

    private Long getTripPageCount(TripPageParam tripPageParam, Session session) {
        return tripMapper.tripPageCount(tripPageParam, session);
    }

}
