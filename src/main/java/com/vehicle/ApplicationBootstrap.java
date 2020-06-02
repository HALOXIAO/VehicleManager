package com.vehicle;

import com.vehicle.framework.core.BeanContainer;
import com.vehicle.framework.exception.ResourceNotFoundException;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
public class ApplicationBootstrap {
    public static void main(String[] args) throws ResourceNotFoundException, IOException {
        BeanContainer beanContainer = new BeanContainer();
        beanContainer.loadBean(ApplicationBootstrap.class.getPackage().getName());
    }
}
