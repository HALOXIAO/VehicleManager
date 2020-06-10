package com.vehicle.framework.mvc.handle;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vehicle.framework.core.BeanContainerFactory;
import com.vehicle.framework.exception.AnnotationException;
import com.vehicle.framework.exception.UserRequestException;
import com.vehicle.framework.mvc.annotation.RequestBody;
import com.vehicle.framework.mvc.annotation.RequestMapping;
import com.vehicle.framework.mvc.annotation.RequestParam;
import com.vehicle.framework.mvc.module.ControllerInfo;
import com.vehicle.framework.mvc.module.PathInfo;
import com.vehicle.framework.mvc.param.RequestChain;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class HandlerMapping implements Handler {

    private final int INIT_SIZE = 256;
    private final Set<Class<?>> controllerClasses;
    private final Map<PathInfo, ControllerInfo> controllerMap = new ConcurrentHashMap<>(INIT_SIZE);

    public HandlerMapping(Set<Class<?>> controllerClasses) {
        this.controllerClasses = controllerClasses;
        settingControllerMap();
    }


    @Override
    public Object handle(RequestChain requestChain) throws Exception {
        return invoke(requestChain);
    }

    //TODO 继续搞
    private void settingControllerMap() {
        controllerClasses.stream().forEach(
                clz -> {
                    String firstPath = "";
                    if (clz.isAnnotationPresent(RequestMapping.class)) {
                        firstPath = clz.getAnnotation(RequestMapping.class).value();
                    }
                    String secondPath = "";
                    String requestPath = "";
                    for (Method method : clz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            secondPath = method.getDeclaredAnnotation(RequestMapping.class).value();
                            requestPath = firstPath + secondPath;
                            int _PCount = 0;
                            int _BCount = 0;
                            Map<String, Class<?>> methodParameter = new ConcurrentHashMap<>(5);
                            for (Parameter parameter : method.getParameters()) {
                                if (parameter.isAnnotationPresent(RequestParam.class)) {
                                    _PCount++;
                                    Class<?> parameterType = parameter.getType();
                                    String name = parameter.getDeclaredAnnotation(RequestParam.class).value();
                                    methodParameter.put(name, parameterType);
                                } else if (parameter.isAnnotationPresent(RequestBody.class)) {
                                    methodParameter.put(parameter.getName(), parameter.getType());
                                    _BCount++;
                                }
// TODO 不能什么都不放,判断Class类型是否为String、int和Integer的其中一个
                            }
                            if (_PCount > 0 && _BCount > 0) {
                                throw new AnnotationException("@RequestParam and @RequestBody can not be together ");
                            } else if (_BCount > 1) {
                                throw new AnnotationException("@RequestBody count max=1");
                            }
                            ControllerInfo controllerInfo = new ControllerInfo(clz, method, methodParameter);
                            PathInfo pathInfo = new PathInfo(method.getDeclaredAnnotation(RequestMapping.class).method(), requestPath);
                            controllerMap.put(pathInfo, controllerInfo);
                        }
                    }

                }
        );

    }

    private Object invoke(RequestChain requestChain) throws InvocationTargetException, IllegalAccessException, IOException {
        PathInfo pathInfo = new PathInfo(requestChain.getRequestMethod(), requestChain.getRequestPath());
        ControllerInfo controllerInfo = controllerMap.get(pathInfo);
        if (controllerInfo == null) {
            return null;
        }
        Object obj = BeanContainerFactory.getBeanContainer().getBean(controllerInfo.getControllerClz());
        if (controllerInfo.getMethodParameter().size() == 0) {
            return controllerInfo.getInvokeMethod().invoke(obj);
        } else {
            Map<String, Class<?>> parameterMap = controllerInfo.getMethodParameter();
            if (controllerInfo.getInvokeMethod().getParameters()[0].isAnnotationPresent(RequestParam.class)) {
                HttpServletRequest request = requestChain.getHttpServletRequest();
                List<Object> parameterList = new ArrayList<>(parameterMap.size());
                Set<String> tempSet = parameterMap.keySet();
                Parameter[] parameters = controllerInfo.getInvokeMethod().getParameters();
                Iterator<String> iterator = tempSet.iterator();
                while (iterator.hasNext()) {
                    String paramName = iterator.next();
                    String temp = request.getParameter(paramName);
                    for (; ; ) {
                        for (Parameter parameter : parameters) {
                            if (temp == null && parameter.getAnnotation(RequestParam.class).require()) {
                                throw new UserRequestException("bad request");
                            }
                        }
                        break;
                    }
                    if (!parameterMap.get(paramName).equals(String.class)) {
                        parameterList.add(Integer.valueOf(temp));
                    } else {
                        parameterList.add(temp);
                    }
                }

                return controllerInfo.getInvokeMethod().invoke(obj, parameterList.toArray());

            } else {
//                @ResultBody
                BufferedReader reader = new BufferedReader(new InputStreamReader(requestChain.getHttpServletRequest().getInputStream(), StandardCharsets.UTF_8));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
//                TODO 这里有反序列化问题,need to test
                Class<?> clz = controllerInfo.getMethodParameter().values().iterator().next();
                if (clz.equals(List.class)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    TypeFactory typeFactory = TypeFactory.defaultInstance();
                    JavaType javaType = typeFactory.constructType(parameterMap.get(clz).getGenericSuperclass());
                    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, javaType);
                    Object result = objectMapper.readValue(builder.toString(), collectionType);
                    return controllerInfo.getInvokeMethod().invoke(obj, result);
                }
                return controllerInfo.getInvokeMethod().invoke(obj, JSON.parseObject(builder.toString(), clz));
            }
        }
        /*
         * ((ParameterizedTypeImpl) controllerInfo.getInvokeMethod().getGenericParameterTypes()[0]).getActualTypeArguments()[0]
         * */

    }

}
