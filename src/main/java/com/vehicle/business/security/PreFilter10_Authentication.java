package com.vehicle.business.security;

/**
 * @author HALOXIAO
 **/
//@WebFilter(urlPatterns = "/*")
public class PreFilter10_Authentication {
/*    @Override
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
    }*/
}
