package com.vehicle.container.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HALOXIAO
 **/
@Getter
@Setter
public class TomcatProperties {

    private String port = "8080";

    private String code = "UTF-8";

    private String hinderUrl = "/";

    private String shiftUrl = "/";

}
