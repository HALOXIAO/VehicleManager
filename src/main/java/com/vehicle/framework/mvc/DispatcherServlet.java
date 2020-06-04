package com.vehicle.framework.mvc;

import com.vehicle.framework.mvc.handle.Handler;
import com.vehicle.framework.mvc.handle.HandlerAdapter;
import com.vehicle.framework.mvc.handle.HandlerMapping;
import com.vehicle.framework.mvc.param.RequestChain;
import com.vehicle.framework.mvc.render.JsonRender;
import com.vehicle.framework.mvc.render.Render;
import com.vehicle.framework.mvc.render.exception.ExceptionRender;
import com.vehicle.framework.mvc.render.exception.GlobalExceptionRender;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class DispatcherServlet extends HttpServlet {

    private final List<Handler> HANDLER = new ArrayList<>();
    private Render resultRender;
    private final List<ExceptionRender> EXCEPTION_RENDER = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        resultRender = new JsonRender();
        HandlerAdapter adapter = new HandlerAdapter();
        HANDLER.add(adapter);
        HANDLER.add(new HandlerMapping(adapter.getControllerClasses()));


        EXCEPTION_RENDER.add(new GlobalExceptionRender());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestChain requestChain = assembleRChain(request, response);
        Iterator<Handler> iterator = HANDLER.iterator();
        Object obj = null;
        for (; ; ) {
            if (!iterator.hasNext()) {
                break;
            }
            Handler handler = iterator.next();
            try {
                obj = handler.handle(requestChain);
                if (obj == null) {
                    throw new RuntimeException("DispatcherServlet exception");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                if (EXCEPTION_RENDER.iterator().hasNext()) {
                    ExceptionRender render = EXCEPTION_RENDER.iterator().next();
                    render.handler(requestChain, e);
                }
            }
        }
        requestChain.setResultBean(obj);
        resultRender.handler(requestChain);
    }


    private RequestChain assembleRChain(HttpServletRequest request, HttpServletResponse response) {
        String requestPath = request.getPathInfo();
        RequestChain requestChain = new RequestChain();
        return requestChain.setHttpServletRequest(request)
                .setHttpServletResponse(response)
                .setRequestMethod(request.getMethod())
                .setResultBean(null)
                .setRequestPath(requestPath);
    }

}
