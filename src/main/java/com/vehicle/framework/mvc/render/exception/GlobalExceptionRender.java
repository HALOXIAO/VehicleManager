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

/**
 * @author HALOXIAO
 **/
@Slf4j
public class GlobalExceptionRender implements ExceptionRender {
    @Override
    public void handler(RequestChain requestChain, Exception e) {
        HttpServletResponse response = requestChain.getHttpServletResponse();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ResultBean<Boolean> bean = new ResultBean<>("fail", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(JSON.toJSONString(bean, SerializerFeature.WriteMapNullValue));
            printWriter.flush();
        } catch (IOException ex) {
            log.error("can not open PrintWriter", ex);
        }
    }
}
