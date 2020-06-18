package com.vehicle.business.controller;

import com.vehicle.business.common.util.SessionUtils;
import com.vehicle.business.manager.SessionManager;
import com.vehicle.business.service.AccountService;
import com.vehicle.common.HTTP_METHOD;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.core.annotation.Autowired;
import com.vehicle.framework.core.annotation.Controller;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * @author HALOXIAO
 **/

@Controller
public class AccountController {

    @Autowired
    public AccountService accountService;

    @Autowired
    public SessionManager sessionManager;

    private final StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
    private final int sessionCookieLength = 10;

    @RequestMapping(value = "/login", method = HTTP_METHOD.HTTP_POST)
    public ResultBean<Boolean> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        String checkPassword = accountService.getPassword(username);
        if (checkPassword == null || !strongPasswordEncryptor.checkPassword(checkPassword, password)) {
            return new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.CHECK_FAIL);
        }
        sessionManager.addSession(username, "SESSION:" + SessionUtils.createSession(sessionCookieLength));
        return new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
