package com.vehicle;

import com.vehicle.container.TomcatContainer;
import com.vehicle.framework.core.BeanContainer;
import com.vehicle.framework.exception.ResourceNotFoundException;
import com.vehicle.service.test;
import org.apache.catalina.LifecycleException;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
public class ApplicationBootstrap {
    public static void main(String[] args) throws ResourceNotFoundException, IOException, LifecycleException {
        BeanContainer beanContainer = new BeanContainer();
        beanContainer.loadBean(ApplicationBootstrap.class.getPackage().getName());
        new TomcatContainer();

    }
}
