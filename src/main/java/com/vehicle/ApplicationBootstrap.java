package com.vehicle;

import com.vehicle.container.TomcatContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.config.BeanConfiguration;
import com.vehicle.framework.exception.ResourceNotFoundException;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author HALOXIAO
 **/
public class ApplicationBootstrap {
    public static void main(String[] args) throws IOException, LifecycleException, InvocationTargetException, IllegalAccessException {
        bootstrap(args);
    }


    private static void bootstrap(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, LifecycleException {
        BeanContainerFactory.getBeanContainer().loadBean(ApplicationBootstrap.class.getPackage().getName());
        new BeanConfiguration();
        new TomcatContainer().startUp();
    }
}
