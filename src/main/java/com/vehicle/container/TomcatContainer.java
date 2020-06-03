package com.vehicle.container;

import com.vehicle.container.properties.TomcatProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TomcatContainer {

    private Tomcat tomcat;
    private HttpServlet httpServlet = new test();


    public TomcatContainer() throws IOException, LifecycleException {
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
        connector.setMaxCookieCount(properties.getCookieCount());
        tomcat.getService().addConnector(connector);
        tomcat.addWebapp("/", webappDir);

    }

}
