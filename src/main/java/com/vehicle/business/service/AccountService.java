package com.vehicle.business.service;

import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.config.HibernateUtilConfig;
import com.vehicle.business.mapper.UserMapper;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Service;
import org.hibernate.Session;

/**
 * @author HALOXIAO
 **/
@Service
public class AccountService {

    @Autowired
    public UserMapper userMapper;

    public String getPassword(String username){
        Session session = HibernateUtilConfig.getSession();
        session.beginTransaction();
       String password=  userMapper.getPassword(username, session);
        SessionUtils.subsequentProcessing(session);
        return password;
    }

}
