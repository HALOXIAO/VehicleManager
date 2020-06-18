package com.vehicle.container;

import com.vehicle.business.security.PreFilter00_Code;
import com.vehicle.container.annotation.WebFilter;
import com.vehicle.container.module.FilterModule;
import com.vehicle.container.properties.TomcatProperties;
import com.vehicle.framework.mvc.DispatcherServlet;
import com.vehicle.framework.util.ClassUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.apache.tomcat.util.scan.StandardJarScanner;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
@Slf4j
public class TomcatContainer {

    private Tomcat tomcat;


    public void startUp(String packageUrl) throws IOException, LifecycleException, ServletException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        log.info("start to init Tomcat");
        initTomcat(packageUrl);
        tomcat.start();
        log.info("tomcat online");
        tomcat.getServer().await();
    }

    private void initTomcat(String packageUrl) throws IOException, ServletException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TomcatProperties properties = new TomcatProperties();
        String userDir = System.getProperty("user.dir"); // 项目目录
        String tomcatBaseDir = userDir + File.separatorChar + "tomcat";
        String webappDir = userDir + File.separatorChar + "target" + File.separatorChar + "classes";
        tomcat = new Tomcat();
        tomcat.setBaseDir(tomcatBaseDir);
        Connector connector = new Connector();
        connector.setPort(Integer.parseInt(properties.getPort()));
        connector.setURIEncoding(properties.getCode());
        connector.setMaxCookieCount(properties.getMaxCookieCount());
        tomcat.getService().addConnector(connector);
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", webappDir);
        ((StandardJarScanner) ctx.getJarScanner()).setScanManifest(false);
        tomcat.addServlet("", "dispatcherServlet", new DispatcherServlet()).setLoadOnStartup(0);
        ctx.addServletMappingDecoded("/*", "dispatcherServlet");
        getFilter(packageUrl).forEach(p -> {
            ctx.addFilterDef(p.getFilterDef());
            ctx.addFilterMap(p.getFilterMap());
        });


    }

    private List<FilterModule> getFilter(String packageUrl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<Class<?>> classes = ClassUtil.getPackageClass(packageUrl);
        List<Class<?>> classList = classes.stream().filter(clz -> {
            if (clz.isAnnotationPresent(WebFilter.class)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        classList.sort(new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> o1, Class<?> o2) {
                int o1c = o1.getAnnotation(WebFilter.class).order();
                int o2c = o2.getAnnotation(WebFilter.class).order();
                return Integer.compare(o1c, o2c);
            }
        });
        List<FilterModule> resultList = new LinkedList<>();
        for (Class<?> clz : classList) {
            Filter filter = (Filter) clz.getConstructor().newInstance();
            FilterMap filterMap = new FilterMap();
            for (String url : clz.getAnnotation(WebFilter.class).urlPattern()) {
                filterMap.addURLPattern(url);
            }
            filterMap.setFilterName(clz.getName());
            FilterDef filterDef = new FilterDef();
            filterDef.setFilterName(clz.getName());
            filterDef.setFilter(filter);
            FilterModule filterModule = new FilterModule(filterMap, filterDef);
            resultList.add(filterModule);
        }
        return resultList;
    }


}
