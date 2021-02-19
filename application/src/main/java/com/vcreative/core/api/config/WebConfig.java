package com.vcreative.core.api.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UndeclaredParamsHandlerInterceptor());
    }

    private static class UndeclaredParamsHandlerInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) {
            if (handler instanceof HandlerMethod
                    && ((HandlerMethod) handler).hasMethodAnnotation(UndeclaredParameters.class)) {

                HandlerMethod handlerMethod = (HandlerMethod) handler;
                checkParams(request, getDeclaredRequestParams(handlerMethod));
            }
            return true;
        }

        private void checkParams(HttpServletRequest request, Set<String> allowedParams) {
            Set<String> requestParameters = request.getParameterMap().keySet();
            Sets.SetView<String> undeclaredParameters = Sets.difference(requestParameters, allowedParams);
            if (undeclaredParameters.size() > 0) {
                String message = String.format("Parameters [%s] do not exist. Valid parameters are [%s]",
                        String.join(", ", undeclaredParameters),
                        String.join(", ", allowedParams));
                throw new InvalidParameterException(message);
            }
        }

        private Set<String> getDeclaredRequestParams(HandlerMethod handlerMethod) {
            Set<String> declaredRequestParams = new HashSet<>();
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

            for (MethodParameter methodParameter : methodParameters) {
                if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
                    RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);
                    if (requestParam != null && StringUtils.hasText(requestParam.value())) {
                        declaredRequestParams.add(requestParam.value());
                    } else {
                        methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
                        declaredRequestParams.add(methodParameter.getParameterName());
                    }
                }
            }
            return declaredRequestParams;
        }
    }
}
