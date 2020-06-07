package com.vehicle;

import com.vehicle.container.TomcatContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.config.BeanConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author HALOXIAO
 **/
@Slf4j
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
