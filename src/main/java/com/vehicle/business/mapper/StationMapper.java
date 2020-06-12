package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Driver;
import com.vehicle.business.module.Station;
import com.vehicle.business.module.convert.StationToStationVO;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.vo.StationVO;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author HALOXIAO
 **/
@Repository
public class StationMapper {


    public List<StationVO> getStationPage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT id, name, address FROM bus_conf_station WHERE status=? LIMIT #{page},#{size} ");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        nativeQuery.addEntity(Station.class);
        transaction.commit();
        session.close();
        List<Station> stations = nativeQuery.list();
        return StationToStationVO.INSTANCE.toStationVOList(stations);
    }

    public Long countStation() {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_station WHERE status=?");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        transaction.commit();
        session.close();
        return nativeQuery.list() == null ? null : nativeQuery.list().size() == 1 ? (Long) nativeQuery.list().get(0) : null;
    }


}
