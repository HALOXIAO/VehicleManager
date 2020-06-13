package com.vehicle;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * @author HALOXIAO
 **/
public class NormalTest {


    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
        List<Integer> test = Arrays.asList(1, 2, 3);
        System.out.println(test.toString());
        System.out.println(Arrays.toString(test.toArray()));
        System.out.println(Arrays.deepToString(test.toArray()));
        System.out.println((test.toString().split(",")).toString());
    }

}
