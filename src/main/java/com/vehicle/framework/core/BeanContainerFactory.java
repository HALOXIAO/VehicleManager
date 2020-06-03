package com.vehicle.framework.core;

import com.vehicle.ApplicationBootstrap;
import com.vehicle.framework.exception.ResourceNotFoundException;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
public class BeanContainerFactory {
    private static volatile BeanContainer beanContainer;

    public static BeanContainer getBeanContainer() {
        if (beanContainer == null) {
            synchronized (BeanContainerFactory.class) {
                if (beanContainer == null) {
                    beanContainer = new BeanContainer();
                }
            }
        }
        return beanContainer;
    }

    private BeanContainerFactory() {

    }


}
