package com.vehicle;

import com.vehicle.framework.bootstrap.Bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class ApplicationBootstrap {
    public static void main(String[] args) throws IOException, LifecycleException, InvocationTargetException, IllegalAccessException, ServletException {
        Bootstrap.bootstrap(args);
    }


}
