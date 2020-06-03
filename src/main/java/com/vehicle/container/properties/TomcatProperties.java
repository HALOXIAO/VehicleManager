package com.vehicle.container.properties;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
@Getter
public class TomcatProperties {
    /**
     * 端口
     */
    private String port = "8080";
    /**
     * 编码
     */
    private String code = "UTF-8";
    /**
     * 最大cookie数量
     */
    private Integer maxCookieCount = 1;


    public TomcatProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(this.getClass().getResource("/application.properties").getPath()));
        port = (String) properties.getOrDefault("tomcat.server.port", port);
        code = (String) properties.getOrDefault("tomcat.server.code", code);
        maxCookieCount = (Integer) properties.getOrDefault("tomcat.server.cookie-count", maxCookieCount);

    }


}
