package com.vehicle;

import com.vehicle.container.TomcatContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.exception.ResourceNotFoundException;
import org.apache.catalina.LifecycleException;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
public class ApplicationBootstrap {
    public static void main(String[] args) throws IOException, LifecycleException {
        BeanContainerFactory.getBeanContainer().loadBean(ApplicationBootstrap.class.getPackage().getName());
        new TomcatContainer().startUp();

    }
}
