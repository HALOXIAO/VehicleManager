package com.vehicle.business.controller;

import com.vehicle.business.module.testMo;
import com.vehicle.comon.HTTP_METHOD;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;

/**
 * @author HALOXIAO
 **/
@Controller
public class TestController {


    @RequestMapping(value = "/a")
    public String asd(@RequestParam("test") String a) {

        return a;
    }

    @RequestMapping(value = "/b")
    public testMo qwe(@RequestBody testMo t) {
        return t;
    }


}
