package com.vehicle.framework.mvc.render;

import com.alibaba.fastjson.JSON;
import com.vehicle.framework.mvc.handle.Handler;
import com.vehicle.framework.mvc.param.RequestChain;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class JsonRender implements Render {

    @Override
    public void handler(RequestChain requestChain) {
        HttpServletResponse response = requestChain.getHttpServletResponse();
        PrintWriter printWriter = null;
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(requestChain.getResultBean()));
            printWriter.flush();
        } catch (IOException e) {
            log.error("can not PrintWriter", e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

}