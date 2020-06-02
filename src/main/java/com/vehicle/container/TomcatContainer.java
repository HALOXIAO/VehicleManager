package com.vehicle.container;

import com.vehicle.container.properties.TomcatProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TomcatContainer {

    private Tomcat tomcat;

    public TomcatContainer() throws IOException {
        init();
    }

    private void init() throws IOException {

    }

    private void initTomcat() throws IOException {
        tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
        TomcatProperties properties = new TomcatProperties();
        connector.setPort(Integer.parseInt(properties.getPort()));
    }

}
