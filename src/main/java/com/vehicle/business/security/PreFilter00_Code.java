package com.vehicle.business.security;

import com.vehicle.container.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HALOXIAO
 **/
@WebFilter(urlPattern = "/*")
@Slf4j
public class PreFilter00_Code implements Filter {

    @Override
    public void doFilter(ServletRequest res, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) res;
        HttpServletResponse response = (HttpServletResponse) rep;
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
