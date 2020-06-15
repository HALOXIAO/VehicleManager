package com.vehicle.business.mapper;

import com.alibaba.fastjson.JSON;
import com.vehicle.business.common.exception.ParamException;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Repository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.*;

/**
 * @author HALOXIAO
 **/
@Repository
@Slf4j
public class StationMapper {


    public List<Station> getStationPage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Query nativeQuery = session.createQuery("SELECT id ,name,address FROM Station WHERE status=?1");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        nativeQuery.setMaxResults(pageParam.getSize());
        nativeQuery.setFirstResult(pageParam.getPage());
        transaction.commit();
        Object[] obj = nativeQuery.list().toArray();
        List<Station> stations = new ArrayList<>(nativeQuery.list().size());
        session.close();
        for (Object object : obj) {
            Object[] temp = (Object[]) object;
            Station station = new Station();
            station.setId((Integer) temp[0]);
            station.setName((String) temp[1]);
            station.setAddress((String) temp[2]);
            stations.add(station);
        }
        return stations;
    }

    public List<Station> getStationPage(Set<String> idSet) {
        String temp = Arrays.toString(idSet.toArray()).replace("[", "").replace("]", "");
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT  id, name, address FROM bus_conf_station WHERE status=? AND id IN (?) ");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        nativeQuery.setParameter(2, temp);
        nativeQuery.addEntity(Station.class);
        transaction.commit();
        session.close();
        List<Station> stations = new ArrayList<>();
        Object[] obj = nativeQuery.list().toArray();

        return stations;
    }

    public Long countStation() {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_station WHERE status=?");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        transaction.commit();
        BigInteger count = nativeQuery.list() == null ? null : nativeQuery.list().size() == 1 ? (BigInteger) nativeQuery.list().get(0) : null;
        session.close();
        Objects.requireNonNull(count);
        return count.longValue();
    }


    public boolean checkRouteStationsExist(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT id FROM bus_conf_station WHERE  id IN(?) LIMIT ?");
        nativeQuery.setParameter(1, ids.toString().replace("[", "").replace("]", ""));
        nativeQuery.setParameter(2, ids.size());
        transaction.commit();
        session.close();
        return nativeQuery.list().size() == ids.size();

    }

    public void addStations(List<Station> station) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        station.forEach(p -> {
            if (p.getId() != null) {
                throw new ParamException("station has id");
            }
            session.save(p);
        });
        transaction.commit();
        session.close();
    }

    public void updateStation(List<Station> stationList) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        stationList.forEach(session::update);
        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
        session.close();
    }


}
