package com.vehicle.business.common.util;

import com.sun.istack.NotNull;
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

}
