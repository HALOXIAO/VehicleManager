package com.vehicle;

import com.vehicle.container.TomcatContainer;
import org.apache.catalina.LifecycleException;
import org.junit.Test;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
public class TomcatTest {

    @Test
    public static void main(String[] args) throws IOException, LifecycleException {
        TomcatContainer tomcatContainer = new TomcatContainer();
    }

}
