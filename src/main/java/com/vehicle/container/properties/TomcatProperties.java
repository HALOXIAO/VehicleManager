package com.vehicle.container.properties;

import com.vehicle.ApplicationBootstrap;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
        URL url  = this.getClass().getResource("/application.properties");
        FileInputStream fileInputStream = new FileInputStream(url.getPath());
        properties.load(fileInputStream);
        port = (String) properties.getOrDefault("tomcat.server.port", port);
        code = (String) properties.getOrDefault("tomcat.server.code", code);
        maxCookieCount = (Integer) properties.getOrDefault("tomcat.server.cookie-count", maxCookieCount);

    }


}
