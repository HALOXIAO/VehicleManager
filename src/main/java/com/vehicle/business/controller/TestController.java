package com.vehicle.business.controller;

import com.vehicle.comon.HTTP_METHOD;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestMapping;

/**
 * @author HALOXIAO
 **/
@Controller
public class TestController {


    @RequestMapping(value = "/HelloWorld")
    public String asd() {

        return "HelloWold";
    }


}
