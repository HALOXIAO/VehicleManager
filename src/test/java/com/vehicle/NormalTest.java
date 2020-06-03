package com.vehicle;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
       Class<?> a = A.class;
//        for (Field field : a.getDeclaredFields())
    }

}
class A{
    B b;
}

class B{

}