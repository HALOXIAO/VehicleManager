package com.vehicle.business.mapper;

import com.sun.istack.NotNull;
import com.vehicle.business.common.util.DateTimeUtils;
import com.vehicle.business.mapper.strategy.*;
import com.vehicle.business.mapper.strategy.impl.TripPageCountAllExistImpl;
import com.vehicle.business.mapper.strategy.impl.TripPageCountAllNonImpl;
import com.vehicle.business.mapper.strategy.impl.TripPageCountNonDateImpl;
import com.vehicle.business.mapper.strategy.impl.TripPageCountNonRouteImpl;
import com.vehicle.business.module.Trip;
import com.vehicle.business.module.param.TripPageParam;
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


    public List<Trip> getTripPage(TripPageParam tripPageParam, Session session) {

        return null;
    }

    public void addTrip(Trip trip, @NotNull Session session) {
        session.save(trip);
    }

    public boolean updateTrip(Trip trip, @NotNull Session session) {
        StringBuilder updateQuery = new StringBuilder("UPDATE Driver SET ");
        Optional<LocalDateTime> dateOptional = Optional.ofNullable(trip.getDate());
        dateOptional.ifPresent((p) -> updateQuery.append(" date= ").append(p.format(DateTimeUtils.TIME_FORMATTER)));
        Optional<Integer> routeIdOptional = Optional.ofNullable(trip.getRouteId());
        routeIdOptional.ifPresent((p) -> updateQuery.append(" routeId= ").append(p));
        Optional<Integer> seatOptional = Optional.ofNullable(trip.getSeats());
        seatOptional.ifPresent((p) -> updateQuery.append(" seats= ").append(p));
        Optional<String> vehicleNumberOptional = Optional.ofNullable(trip.getVehicleNumber());
        vehicleNumberOptional.ifPresent((p) -> updateQuery.append(" vehicleNumber= ").append(p));
        Optional<Integer> statusOptional = Optional.ofNullable(trip.getStatus());
        statusOptional.ifPresent((p) -> updateQuery.append(" status= ").append(trip.getStatus()));
        updateQuery.append(" WHERE id= ").append(trip.getId());
        Query query = session.createQuery(updateQuery.toString());
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
