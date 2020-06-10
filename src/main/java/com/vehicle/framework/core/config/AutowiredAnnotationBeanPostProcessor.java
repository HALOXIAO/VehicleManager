package com.vehicle.framework.core.config;

import com.vehicle.framework.core.BeanContainer;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.exception.ClassNotLoadException;
import com.vehicle.framework.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class AutowiredAnnotationBeanPostProcessor {
    private static final List<Class<? extends Annotation>> WIRED_ANNOTATION = Arrays.asList(Autowired.class);

    BeanContainer beanContainer;

    public AutowiredAnnotationBeanPostProcessor() {
        beanContainer = BeanContainerFactory.getBeanContainer();
    }

    public void loadAutowired(String packageUrl) {
        if (!beanContainer.isLoad()) {
            throw new ClassNotLoadException("class not load,can not autowired");
        }
        Set<Class<?>> classes = ClassUtil.getPackageClass(packageUrl);
        classes.stream().forEach(clz -> {
            if (beanContainer.canRegister(clz)) {
                try {
                    findAutowiringMetadata(clz);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException();
                }
            }
        });

    }


    private void findAutowiringMetadata(Class<?> clz) throws IllegalAccessException {
        for (Field field : clz.getDeclaredFields()) {
            for (Class<? extends Annotation> wiredAnnotation : WIRED_ANNOTATION) {
                if (field.isAnnotationPresent(wiredAnnotation)) {
                    if (null == beanContainer.getBean(clz) || null == beanContainer.getBean(field.getType())) {
                        log.warn("field class not load:" + field.getType().getName());
                        throw new ClassNotLoadException("field class not load:" + field.getType().getName());
                    } else {
                        instantiationAutoWired(field, clz);
                    }
                }
            }

        }
    }


    private void instantiationAutoWired(Field field, Class<?> clz) throws IllegalAccessException {
        Object o1 = beanContainer.getBean(field.getType());
        Object o2 = beanContainer.getBean(clz);
        if (!field.canAccess(o2)) {
            log.error("Access error" + o1.toString());
            throw new IllegalAccessException();
        }
        field.set(o2, o1);
        beanContainer.addBean(clz, o2);
    }


}
