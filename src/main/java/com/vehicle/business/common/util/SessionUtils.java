package com.vehicle.business.common.util;

import com.sun.istack.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

/**
 * @author HALOXIAO
 **/
public class SessionUtils {

    public static void subsequentProcessing(@NotNull Session session) {
        try (session) {
            session.getTransaction().commit();
        }
    }

    public static String createSession(int length){
       return  RandomStringUtils.randomAlphanumeric(length);
    }


}
