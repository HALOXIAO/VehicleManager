package com.vehicle.business.controller;

import com.vehicle.comon.HTTP_METHOD;
import com.vehicle.comon.ResultBean;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;

/**
 * @author HALOXIAO
 **/

@Controller
public class AccountController {


    @RequestMapping(value = "/login", method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        return null;
    }

}
