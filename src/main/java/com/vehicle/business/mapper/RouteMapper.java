package com.vehicle.business.mapper;

import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.business.module.param.RouteParam;
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
public class RouteMapper {
    public List<Route> getRoutePage(PageParam pageParam) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        NativeQuery routeQuery = session.createSQLQuery("SELECT id,  detail, name  from bus_conf_route WHERE status=? LIMIT #{page},#{size}");
        routeQuery.addEntity(Route.class);
        routeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL);
        transaction.commit();
        session.close();
        return routeQuery.getResultList();
    }

    public void addRoute(Route route) {
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(route);
        transaction.commit();
        session.close();
    }

    public void updateRoute(Route route){
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.update(route);
        transaction.commit();
        session.close();
    }

    public void deleteRoutes(List<Integer>ids){
        Session session = HibernateUtilConfig.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ids.forEach(
                session::delete
        );
        transaction.commit();
        session.close();
    }

}
