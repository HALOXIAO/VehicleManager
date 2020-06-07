package com.vehicle.framework.util;

/**
 * @author HALOXIAO
 **/
public class CommonUtil {

    /**
     * 字符串全小写
    * */
    public static String toLowWords(String words){
            char[] charArray = words.toCharArray();
            charArray[0] += 32;
            return String.valueOf(charArray);
    }

}
