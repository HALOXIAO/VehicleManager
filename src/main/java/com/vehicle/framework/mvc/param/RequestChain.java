package com.vehicle.framework.mvc.param;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HALOXIAO
 **/
@Getter
public class RequestChain {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String requestPath;
    private String requestMethod;
    private Object resultBean;

    public RequestChain setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

    public RequestChain setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        return this;

    }

    public RequestChain setRequestPath(String requestPath) {
        this.requestPath = requestPath;
        return this;

    }

    public RequestChain setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;

    }

    public RequestChain setResultBean(Object resultBean) {
        this.resultBean = resultBean;
        return this;

    }
}
