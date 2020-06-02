package com.vehicle.container.properties;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
@Getter
public class TomcatProperties {

    private String port = "8080";

    private String code = "UTF-8";

    private String hinderUrl = "/";

    private String shiftUrl = "/";

    private final Properties properties;

    public TomcatProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(this.getClass().getResource("/application.properties").getPath()));
        port = (String) properties.getOrDefault("tomcat.server.port", port);
        code = (String) properties.getOrDefault("tomcat.server.code", code);
        hinderUrl = (String) properties.getOrDefault("tomcat.server.hinder-url", hinderUrl);
        shiftUrl = (String) properties.getOrDefault("tomcat.server.shift-utl", shiftUrl);
    }


}
