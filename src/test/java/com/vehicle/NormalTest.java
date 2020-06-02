package com.vehicle;

import org.junit.Test;

import java.net.URL;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public static void main(String[] args) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("E:/JAVA_Project/School-Web/VehicleManager/src/test/java/com/vehicle.java");
        System.out.println(url.toString());
    }

}
