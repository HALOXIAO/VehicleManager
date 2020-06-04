package com.vehicle.container;

import com.vehicle.container.properties.TomcatProperties;
import com.vehicle.framework.mvc.DispatcherServlet;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TomcatContainer {

    private Tomcat tomcat;

    public void startUp() throws IOException, LifecycleException {
        initTomcat();
        tomcat.start();
        tomcat.getServer().await();
    }

    private void initTomcat() throws IOException {
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
        tomcat.addServlet("", "dispatcherServlet", new DispatcherServlet()).setLoadOnStartup(0);
        ctx.addServletMappingDecoded("/*","dispatcherServlet");
    }

}
