package com.vehicle.framework.mvc.render;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vehicle.common.RESULT_BEAN_STATUS_CODE;
import com.vehicle.common.ResultBean;
import com.vehicle.framework.mvc.param.RequestChain;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class JsonRender implements Render {

    @Override
    public void handler(RequestChain requestChain) {
        HttpServletResponse response = requestChain.getHttpServletResponse();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(JSON.toJSONString(requestChain.getResultBean(),SerializerFeature.WriteMapNullValue));
            printWriter.flush();
        } catch (IOException e) {
            log.error("can not open PrintWriter", e);
        }

    }

}
