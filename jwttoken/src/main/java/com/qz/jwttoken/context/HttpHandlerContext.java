package com.qz.jwttoken.context;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 用户信息上下文
 */
public class HttpHandlerContext {
    private static ThreadLocal<ServletRequest> requestContext = new ThreadLocal<>();
    private static ThreadLocal<ServletResponse> responseContext = new ThreadLocal<>();


    public static ServletRequest getRequestContext() {
        return requestContext.get();
    }

    public static void setRequestContext(ServletRequest servletRequest) {
        requestContext.set(servletRequest);
    }

    public static ServletResponse getResponseContext() {
        return responseContext.get();
    }

    public static void setResponseContext(ServletResponse servletResponse) {
        responseContext.set(servletResponse);
    }
}
