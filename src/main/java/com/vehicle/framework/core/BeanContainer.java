package com.vehicle.framework.core;

import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Component;
import com.vehicle.framework.core.annotation.Repository;
import com.vehicle.framework.core.annotation.Service;
import com.vehicle.framework.exception.ClassNotLoadException;
import com.vehicle.framework.exception.ResourceNotFoundException;
import com.vehicle.framework.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author HALOXIAO
 **/
@Slf4j
public class BeanContainer {

    private static final int INIT_SIZE = 256;
    private final static Map<Class<?>, Object> application = new ConcurrentHashMap<>(INIT_SIZE);
    private final Map<Class<?>, Object> fieldApplication = new ConcurrentHashMap<>(INIT_SIZE);
    private boolean isLoad = false;


    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Service.class, Repository.class);
    private static final List<Class<? extends Annotation>> WIRED_ANNOTATION = Arrays.asList(Autowired.class);

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

    /**
     * 通过Class获取bean
     *
     * @param cla 目标bean的Class
     * @return 实例化的目标bean
     */
    public Object getBean(Class<?> cla) {
        return application.get(cla);
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
        isLoad = true;
    }

    /**
     * (还没搞完，别调用)
     * 自动注入功能
     * @param packageUrl 扫描的包路径
     * */
    @Deprecated
    //  TODO Autowired
    public void autowired(String packageUrl) {
        if (!isLoad) {
            throw new ClassNotLoadException("class not load,can not autowired");
        }
        Set<Class<?>> classes = ClassUtil.getPackageClass(packageUrl);
        classes.stream().filter(clz -> {
                    for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                        if (clz.isAnnotationPresent(annotation)) {
                            for (Field field : clz.getDeclaredFields()) {
                                for (Class<? extends Annotation> wiredAnnotation : WIRED_ANNOTATION) {
                                    if (field.isAnnotationPresent(wiredAnnotation)) {
                                        if (null == application.get(clz)) {
                                            log.warn("field class not load:" + field.getType().getName());
                                            throw new ClassNotLoadException("field class not load:" + field.getType().getName());
                                        }
                                    }
                                }
                            }
                            return true;
                        }
                    }
                    return false;
                }
        ).forEach(clz -> {
            for (Field field : clz.getDeclaredFields()) {
                for (Class<? extends Annotation> wiredAnnotation : WIRED_ANNOTATION) {
                    if (field.isAnnotationPresent(wiredAnnotation)) {
//                        TODO
                    }
                }
            }
        });


    }

}


