package com.vehicle.framework.bootstrap;

import com.vehicle.ApplicationBootstrap;
import com.vehicle.container.TomcatContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.config.AutowiredAnnotationBeanPostProcessor;
import com.vehicle.framework.core.config.BeanConfiguration;
import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author HALOXIAO
 **/
public class Bootstrap {
    public static void bootstrap(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, LifecycleException, ServletException, NoSuchMethodException, InstantiationException {
        BeanContainerFactory.getBeanContainer().loadBean(ApplicationBootstrap.class.getPackage().getName());
        new BeanConfiguration();
        new AutowiredAnnotationBeanPostProcessor().loadAutowired(ApplicationBootstrap.class.getPackage().getName());
        new TomcatContainer().startUp(ApplicationBootstrap.class.getPackage().getName());
    }

}
