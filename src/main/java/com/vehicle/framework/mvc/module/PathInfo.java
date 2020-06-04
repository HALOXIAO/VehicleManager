package com.vehicle.framework.mvc.module;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * @author HALOXIAO
 **/
@Data
@AllArgsConstructor
public class PathInfo {

    private String httpMethod;

    private String requestPath;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathInfo pathInfo = (PathInfo) o;
        return getHttpMethod().equals(pathInfo.getHttpMethod()) &&
                getRequestPath().equals(pathInfo.getRequestPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHttpMethod(), getRequestPath());
    }
}
