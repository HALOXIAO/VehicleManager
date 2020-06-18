package com.vehicle.business.mapper;

import com.vehicle.framework.core.annotation.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author HALOXIAO
 * @since 2020-06-18 18:57
 **/
@Repository
public class UserMapper {

    public String getPassword(String username, Session session) {
        Query query = session.createQuery("SELECT passWord FROM User WHERE userName=?1");
        query.setParameter(1, username);
        Object[] temp = query.list().toArray();
        String password = null;
        for (int i = 0; i < temp.length - 1; i++) {
            password = (String) temp[i];
        }
        return password;
    }

}
