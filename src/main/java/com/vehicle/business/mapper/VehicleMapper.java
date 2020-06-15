package com.vehicle.business.mapper;

import com.vehicle.business.common.exception.ParamException;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Vehicle;
import com.vehicle.framework.core.annotation.Repository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Repository
@Slf4j
public class VehicleMapper {

    public void deleteVehicles(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ids.forEach(id->{
            Vehicle vehicle = new Vehicle();
            vehicle.setId(id);
            session.delete(vehicle);
        });
        try {
            transaction.commit();
        }catch (Exception e){
            log.error(Arrays.toString(e.getStackTrace()));
            transaction.rollback();
            session.close();
        }
    }




}
