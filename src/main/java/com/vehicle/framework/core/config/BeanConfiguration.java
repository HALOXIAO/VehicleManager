package com.vehicle.framework.core.config;

import com.vehicle.framework.core.BeanContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.annotation.Bean;
import com.vehicle.framework.core.annotation.Configuration;
import org.apache.naming.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HALOXIAO
 **/
public class BeanConfiguration {

    public BeanConfiguration() throws InvocationTargetException, IllegalAccessException {
        BeanContainer application = BeanContainerFactory.getBeanContainer();
        loadBean(application);
    }

    private void loadBean(BeanContainer application) throws InvocationTargetException, IllegalAccessException {
        Set<Class<?>> beanSet = application.getBeansByAnnotation(Configuration.class);
        Iterator<Class<?>> iterator = beanSet.iterator();
        while (iterator.hasNext()) {
            Class<?> clz = iterator.next();
            Object o = application.getBean(clz);
            for (Method method : o.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Bean.class)) {
                    Object result = method.invoke(o);
                    application.addBean(method.getReturnType(), result);
                }
            }
            application.deleteBean(clz);
        }


    }


}
