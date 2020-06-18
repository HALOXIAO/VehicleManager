package com.vehicle.business.mapper;

import com.sun.istack.NotNull;
import com.vehicle.business.common.util.DateTimeUtils;
import com.vehicle.business.mapper.strategy.*;
import com.vehicle.business.mapper.strategy.impl.*;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.TripPageParam;
import com.vehicle.business.module.vo.TripVO;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author HALOXIAO
 **/
@Repository
public class TripMapper {


    public List<TripVO> getTripPage(@NotNull TripPageParam tripPageParam, @NotNull Session session) {
        if (tripPageParam.getDate() != null && tripPageParam.getRoute() != null) {
            TripPageStrategy tripPage = new TripPageAllExistImpl();
            return tripPage.tripPage(tripPageParam, session);
        } else if (tripPageParam.getDate() == null && tripPageParam.getRoute() == null) {
            TripPageStrategy tripPage = new TripPageAllNonImpl();
            return tripPage.tripPage(tripPageParam, session);
        } else if (tripPageParam.getRoute() != null) {
            TripPageStrategy tripPage = new TripPageNonDateImpl();
            return tripPage.tripPage(tripPageParam, session);
        } else {
            TripPageStrategy tripPage = new TripPageNonRouteImpl();
            return tripPage.tripPage(tripPageParam, session);
        }
    }

    public void addTrip(Trip trip, @NotNull Session session) {
        session.save(trip);
    }

    public boolean updateTrip(Trip trip, @NotNull Session session) {
        StringBuilder updateQuery = new StringBuilder("UPDATE Driver SET ");
        Optional<LocalDateTime> dateOptional = Optional.ofNullable(trip.getDate());
        dateOptional.ifPresent((p) -> updateQuery.append(" date=?1  ,"));
        Optional<Integer> routeIdOptional = Optional.ofNullable(trip.getRouteId());
        routeIdOptional.ifPresent((p) -> updateQuery.append(" routeId=?2  ,"));
        Optional<Integer> seatOptional = Optional.ofNullable(trip.getSeats());
        seatOptional.ifPresent((p) -> updateQuery.append(" seats=?3  , "));
        Optional<String> vehicleNumberOptional = Optional.ofNullable(trip.getVehicleNumber());
        vehicleNumberOptional.ifPresent((p) -> updateQuery.append(" vehicleNumber=?4  ,"));
        Optional<Integer> statusOptional = Optional.ofNullable(trip.getStatus());
        statusOptional.ifPresent((p) -> updateQuery.append(" status=?5  , "));
        updateQuery.deleteCharAt(updateQuery.lastIndexOf(","));
        updateQuery.append(" WHERE id=?6   ").append(trip.getId());
        Query query = session.createQuery(updateQuery.toString());
        if (dateOptional.isPresent()) {
            query.setParameter(1, trip.getDate().format(DateTimeUtils.TIME_FORMATTER));
        }
        if (routeIdOptional.isPresent()) {
            query.setParameter(2, trip.getRouteId());
        }
        if(seatOptional.isPresent()){
            query.setParameter(3, trip.getSeats());
        }
        if(vehicleNumberOptional.isPresent()){
            query.setParameter(4, trip.getVehicleNumber());
        }
        if(statusOptional.isPresent()){
            query.setParameter(5, trip.getStatus());
        }
        query.setParameter(6, trip.getId());
        int row = query.executeUpdate();
        return row == 1;
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
