package com.vehicle.business.mapper;

import com.sun.istack.NotNull;
import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.module.Route;
import com.vehicle.business.module.param.PageParam;
import com.vehicle.common.status.DATABASE_COMMON_STATUS_CODE;
import com.vehicle.framework.core.annotation.Repository;
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
public class RouteMapper {
    public List<Route> getRoutePage(PageParam pageParam, @NotNull Session session) {
        Query routeQuery = session.createQuery("SELECT id,  detail, name  from Route WHERE status=?1 ");
        routeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        routeQuery.setMaxResults(pageParam.getSize());
        routeQuery.setFirstResult(pageParam.getPage());
        Object[] temp = routeQuery.list().toArray();
        List<Route> routeList = new ArrayList<>(temp.length);
        for (Object obj : temp) {
            Object[] object = (Object[]) obj;
            Route route = new Route();
            route.setId((Integer) object[0]);
            route.setDetail((String) object[1]);
            route.setName((String) object[2]);
            routeList.add(route);
        }
        return routeList;
    }

    public Long routeCount() {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        NativeQuery nativeQuery = session.createSQLQuery("SELECT COUNT(*)FROM bus_conf_route WHERE status=?");
        nativeQuery.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        BigInteger count = nativeQuery.list() == null ? null : nativeQuery.list().size() == 1 ? (BigInteger) nativeQuery.list().get(0) : null;
        SessionUtils.subsequentProcessing(session);
        Objects.requireNonNull(count);
        return count.longValue();
    }

    public void addRoute(Route route) {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        session.save(route);
        SessionUtils.subsequentProcessing(session);
    }

    public boolean updateRoute(Route route, Session session) {
        StringBuilder hqlBuilder = new StringBuilder("UPDATE Route SET ");
        hqlBuilder.append(route.getStartId() != null ? " startId=?1 ," : "");
        hqlBuilder.append(route.getEndId() != null ? " endId=?2 ," : "");
        hqlBuilder.append(route.getDetail() != null ? " detail=?3 ," : "");
        hqlBuilder.append(route.getName() != null ? " name=?4 ," : "");
        hqlBuilder.append(route.getStatus() != null ? " status=?5 ," : "");
        hqlBuilder.deleteCharAt(hqlBuilder.lastIndexOf(","));
        hqlBuilder.append("WHERE id=?6");
        Query query = session.createQuery(hqlBuilder.toString());
        if (route.getStartId() != null) {
            query.setParameter(1, route.getStartId());
        }
        if (route.getEndId() != null) {
            query.setParameter(2, route.getEndId());
        }
        if (route.getDetail() != null) {
            query.setParameter(3, route.getDetail());
        }
        if (route.getName() != null) {
            query.setParameter(4, route.getName());
        }
        if (route.getStatus() != null) {
            query.setParameter(5, route.getStatus());
        }
        query.setParameter(6, route.getId());
        return query.executeUpdate() == 1;
    }

    public void deleteRoutes(List<Integer> ids) {
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
        ids.forEach(
                session::delete
        );
        SessionUtils.subsequentProcessing(session);
    }

    public Route getRoute(Integer id, Session session) {
        Query query = session.createQuery("SELECT id,detail,name  FROM Route  WHERE status=?1");
        query.setParameter(1, DATABASE_COMMON_STATUS_CODE.NORMAL.getValue());
        List temp = query.list();
        return (Route) temp.get(0);
    }


}
