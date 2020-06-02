package com.vehicle.container;

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

    private void initTomcat() {
        tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
    }

}
