package com.vehicle.business.security;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.container.annotation.WebFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author HALOXIAO
 **/
@WebFilter(urlPattern = "/*", order = Integer.MIN_VALUE)
public class PreFilter10_Authentication implements Filter {
    @Override
    public void doFilter(ServletRequest res, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) res;
        HttpServletResponse response = (HttpServletResponse) rep;
        if (request.getContextPath().contains("/login")) {
            chain.doFilter(request, response);
        }
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(httpSession.getId()) == null) {
            try (PrintWriter printWriter = response.getWriter()) {
                ResultBean<Boolean> bean = new ResultBean<>("no login", RESULT_BEAN_STATUS_CODE.NO_LOGIN);
                printWriter.write(JSON.toJSONString(bean));
                printWriter.flush();
            }
        }
        chain.doFilter(request, response);
    }
}
