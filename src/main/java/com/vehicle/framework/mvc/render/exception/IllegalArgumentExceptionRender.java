package com.vehicle.framework.mvc.render.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.mvc.param.RequestChain;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class IllegalArgumentExceptionRender implements ExceptionRender {

    @Override
    public void handler(RequestChain requestChain, Exception e) {
        if (!e.getCause().getClass().equals(e.getClass())) {
            return;
        }
        log.warn(Arrays.toString(e.getStackTrace()));
        ResultBean<Boolean> bean = new ResultBean<>("argument exception", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        HttpServletResponse response = requestChain.getHttpServletResponse();
        response.setStatus(HttpServletResponse.SC_OK);
        String result = JSON.toJSONString(bean, SerializerFeature.WriteMapNullValue);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(result);
            printWriter.flush();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }
}
