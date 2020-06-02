package com.vehicle.framework.util;

/**
 * @author HALOXIAO
 **/
public class ValidateUtil {

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public  static boolean isEmpty(String str){
        return (str == null || "".equals(str));
    }



}
