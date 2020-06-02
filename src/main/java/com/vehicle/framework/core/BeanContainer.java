package com.vehicle.framework.core;

import com.vehicle.framework.core.annotation.Component;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.exception.ResourceNotFoundException;
import com.vehicle.framework.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author HALOXIAO
 **/
@Slf4j
public class BeanContainer {

    private final int INIT_SIZE = 256;
    private final Map<Class<?>, Object> application = new ConcurrentHashMap<>(INIT_SIZE);
    private boolean isLoad = false;


    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class);


    public Set<Object> getBeans() {
        return new HashSet<>(
                application.values());
    }

    public Object deleteBean(Class<?> cla) {
        return application.remove(cla);
    }

    public Object addBean(Class<?> cla, Object obj) {
        return application.put(cla, obj);
    }

    public Object getBean(Class<?> cla) {
        return application.get(cla);
    }

    public int beanSize() {
        return application.size();
    }


    public void loadBean(String packageUrl) throws ResourceNotFoundException, IOException {
        if (isLoad) {
            log.warn("Already load bean");
            return;
        }
        Set<Class<?>> classes = ClassUtil.getPackageClass(packageUrl);
        classes.stream().filter(clz -> {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                if (clz.isAnnotationPresent(annotation)) {
                    return true;
                }
            }
            return false;
        }).forEach(clz -> {
            application.put(clz, ClassUtil.newInstance(clz));
        });
        isLoad = true;
    }

}


