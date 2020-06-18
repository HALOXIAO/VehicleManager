package com.vehicle.business.mapper;

import com.sun.istack.NotNull;
import com.vehicle.business.module.Vehicle;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.framework.core.annotation.Repository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HALOXIAO
 **/
@Repository
@Slf4j
public class VehicleMapper {

    public void deleteVehicles(List<Integer> ids, Session session) {
        ids.forEach(id -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(id);
            session.delete(vehicle);
        });
    }

    public List<Vehicle> getVehiclePage(@NotNull PageParam pageParam, @NotNull Session session) {
        Query query = session.createQuery("SELECT id,vehicleNumber,vehicleModel,seatCount FROM Vehicle ");
        query.setMaxResults(pageParam.getSize());
        query.setFirstResult(pageParam.getPage());
        Object[] temp = query.list().toArray();
        List<Vehicle> resultList = new ArrayList<>(temp.length);
        for (Object objTemp : temp) {
            Object[] objects = (Object[]) objTemp;
            Vehicle vehicle = new Vehicle();
            vehicle.setId((Integer) objects[0]);
            vehicle.setVehicleNumber((String) objects[1]);
            vehicle.setVehicleModel((String) objects[2]);
            vehicle.setSeatCount((Integer) objects[3]);
            resultList.add(vehicle);
        }
        return resultList;

    }

    public Long getVehiclePageCount(@NotNull Session session) {
        NativeQuery nativeQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_vehicle ");
        BigInteger count = nativeQuery.list() == null ? null : nativeQuery.list().size() == 1 ? (BigInteger) nativeQuery.list().get(0) : null;
        Objects.requireNonNull(count);
        return count.longValue();
    }

    public boolean updateVehicle(Vehicle vehicle, Session session) {
        StringBuilder hqlBuilder = new StringBuilder("UPDATE Vehicle SET ");
        hqlBuilder.append(vehicle.getSeatCount() != null ? " seatCount=?1 ," : "");
        hqlBuilder.append(vehicle.getVehicleNumber() != null ? " vehicleNumber=?2 ," : "");
        hqlBuilder.append(vehicle.getVehicleModel() != null ? " vehicleModel=?3 ," : "");
        hqlBuilder.deleteCharAt(hqlBuilder.lastIndexOf(","));
        hqlBuilder.append(" WHERE id = ?4");
        Query query = session.createQuery(hqlBuilder.toString());
        if (vehicle.getSeatCount() != null) {
            query.setParameter(1, vehicle.getSeatCount());
        }
        if (vehicle.getVehicleNumber() != null) {
            query.setParameter(2, vehicle.getVehicleNumber());
        }
        if (vehicle.getVehicleModel() != null) {
            query.setParameter(3, vehicle.getVehicleModel());
        }
        query.setParameter(4, vehicle.getId());
        return query.executeUpdate() == 1;
    }

    public void addVehicle(Vehicle vehicle, Session session) {
        session.save(vehicle);

    }


}
