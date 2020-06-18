package com.vehicle.framework.mvc;

import com.vehicle.common.HTTP_METHOD;
import com.vehicle.framework.exception.UrlNotFoundException;
import com.vehicle.framework.mvc.handle.Handler;
import com.vehicle.framework.mvc.handle.HandlerAdapter;
import com.vehicle.framework.mvc.handle.HandlerMapping;
import com.vehicle.framework.mvc.param.RequestChain;
import com.vehicle.framework.mvc.render.JsonRender;
import com.vehicle.framework.mvc.render.Render;
import com.vehicle.framework.mvc.render.exception.ExceptionRender;
import com.vehicle.framework.mvc.render.exception.GlobalExceptionRender;
import com.vehicle.framework.mvc.render.exception.IllegalArgumentExceptionRender;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author HALOXIAO
 **/
@Slf4j
@WebServlet("/*")
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
        EXCEPTION_RENDER.add(new IllegalArgumentExceptionRender());
        EXCEPTION_RENDER.add(new GlobalExceptionRender());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (OptionsHandler(request, response)) {
            return;
        }


        RequestChain requestChain = assembleRChain(request, response);
        Iterator<Handler> iterator = HANDLER.iterator();
        Object obj = null;
        while (iterator.hasNext()) {
            Handler handler = iterator.next();
            try {
                obj = handler.handle(requestChain);
                if (obj == null) {
                    throw new UrlNotFoundException("URL not found exception:" + requestChain.toString());
                }
            } catch (Exception e) {
                log.error(Arrays.toString(e.getStackTrace()));
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

    private boolean OptionsHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getMethod().equals(HTTP_METHOD.HTTP_OPTIONS)) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.flush();
            }
            return true;
        }
        return false;
    }


}
