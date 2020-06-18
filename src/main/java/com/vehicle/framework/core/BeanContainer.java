package com.vehicle.framework.core;

import com.vehicle.framework.core.annotation.*;
import com.vehicle.framework.exception.ClassNotLoadException;
import com.vehicle.framework.exception.ResourceNotFoundException;
import com.vehicle.framework.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * @author HALOXIAO
 **/
@Slf4j
public class BeanContainer {

    //    TODO log需要记录更多Infomation
//    TODO 优化：依据Bean的名字注入
    private static final int INIT_SIZE = 256;
    private final static Map<Class<?>, Object> application = new ConcurrentHashMap<>(INIT_SIZE);
    private boolean isLoad = false;

    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Service.class, Repository.class, Controller.class, Configuration.class);

    /**
     * 获取所有的bean
     */
    public Set<Object> getBeans() {
        return new HashSet<>(
                application.values());
    }

    /**
     * 通过Class删除bean
     *
     * @param cla 目标bean的Class
     * @return 删除目标的值，如果不存在{@code key}，则返回{@code null}
     */
    public Object deleteBean(Class<?> cla) {
        return application.remove(cla);
    }

    /**
     * 添加bean
     *
     * @param cla bean的Class
     * @param obj 已实例化的bean
     * @return 返回旧值，可以为{@code null}
     */
    public Object addBean(Class<?> cla, Object obj) {
        return application.put(cla, obj);
    }

    public boolean canRegister(Class<?> clz) {
        for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
            if (clz.isAnnotationPresent(annotation)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 通过Class获取bean
     *
     * @param cla 目标bean的Class
     * @return 实例化的目标bean
     */
    public Object getBean(Class<?> cla) {
        return application.get(cla);
    }

    public Set<Class<?>> getBeansByAnnotation(Class<? extends Annotation> annotation) {
        return application.keySet().stream().filter(
                clz -> {
                    if (clz.isAnnotationPresent(annotation)) {
                        return true;
                    }
                    return false;
                }
        ).collect(Collectors.toSet());
    }



    /**
     * 是否加载
     */
    public boolean isLoad() {
        return isLoad;
    }

    /**
     * 返回bean的数量
     *
     * @return bean数量
     */
    public int beanSize() {
        return application.size();
    }

    /**
     * 加载Application，只需要加载一次
     *
     * @param packageUrl 扫描的包路径
     */
    public void loadBean(String packageUrl) {
        if (isLoad) {
            log.warn("Already load bean");
            return;
        }
        log.info("start to load bean");
        Set<Class<?>> classes = ClassUtil.getPackageClass(packageUrl);
        classes.stream().filter(clz -> {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                if (clz.isAnnotationPresent(annotation)) {
                    return true;
                }
            }
            return false;
        }).forEach(clz -> {
            addBean(clz, ClassUtil.newInstance(clz));
        });
        log.info("Bean" + application.toString());
        isLoad = true;
    }



}


