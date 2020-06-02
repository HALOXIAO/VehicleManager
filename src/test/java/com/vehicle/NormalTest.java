package com.vehicle;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ApplicationBootstrap.class.getResource("/application.properties").getPath()));
        
        System.out.println(ApplicationBootstrap.class.getResource("/application.properties").toString());
    
    }

}
