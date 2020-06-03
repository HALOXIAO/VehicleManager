package com.vehicle.framework.preprocessing;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HALOXIAO
 **/
@WebFilter(urlPatterns = "/*")
public class ContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest res, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) res;
        HttpServletResponse response = (HttpServletResponse) rep;


    }
}
