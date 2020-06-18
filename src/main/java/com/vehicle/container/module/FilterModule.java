package com.vehicle.container.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

/**
 * @author HALOXIAO
 * @since 2020-06-18 11:23
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterModule {

    private FilterMap filterMap;
    private FilterDef filterDef;
}
