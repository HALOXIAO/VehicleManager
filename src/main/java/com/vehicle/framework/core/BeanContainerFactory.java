package com.vehicle.framework.core;

/**
 * @author HALOXIAO
 **/
public class BeanContainerFactory {
    private static volatile BeanContainer beanContainer=null;

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
