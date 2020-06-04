package com.vehicle.framework.mvc;

import com.vehicle.framework.mvc.handle.Handler;
import com.vehicle.framework.mvc.handle.HandlerAdapter;
import com.vehicle.framework.mvc.handle.HandlerMapping;
import com.vehicle.framework.mvc.param.RequestChain;
import com.vehicle.framework.mvc.render.Render;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
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
    private final List<Render> RENDER = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        HANDLER.add(new HandlerAdapter());
        HANDLER.add(new HandlerMapping());

    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestChain requestChain = assembleRChain(request, response);
        Iterator<Handler> iterator = HANDLER.iterator();
        for (; ; ) {
            if (!iterator.hasNext()) {
                break;
            }

        }
    }

    private RequestChain assembleRChain(HttpServletRequest request, HttpServletResponse response) {
        String requestPath = request.getServletPath();
        RequestChain requestChain = new RequestChain();
       return requestChain.setHttpServletRequest(request)
                .setHttpServletResponse(response)
                .setRequestMethod(request.getMethod())
                .setResultBean(null)
                .setRequestPath(requestPath);
    }
}
