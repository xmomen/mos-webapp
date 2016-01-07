package com.xmomen.mos.module.core.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.web.exceptions.NotFoundResourcesException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeng on 2016/1/7.
 */
public class FormAuthenticationFilterExt extends FormAuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(FormAuthenticationFilterExt.class);

    private void jsonHandle(String message, ServletRequest request, ServletResponse response){
        try {
            Map map = new HashMap<String, Object>();
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("message", message);
            map.put("timestamp", new Date());
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = httpServletResponse.getWriter();
            out.println(JSONObject.toJSONString(map));
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                if (!"XMLHttpRequest"
                        .equalsIgnoreCase(((HttpServletRequest) request)
                                .getHeader("X-Requested-With"))) {// 不是ajax请求
                    //allow them to see the login page ;)
                    return true;
                } else {
                    jsonHandle("Requires authentication", request, response);
                }
                return false;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            if (!"XMLHttpRequest"
                    .equalsIgnoreCase(((HttpServletRequest) request)
                            .getHeader("X-Requested-With"))) {// 不是ajax请求
                saveRequestAndRedirectToLogin(request, response);
            } else {
                jsonHandle("Requires authentication", request, response);
            }
            return false;
        }
    }

    /**
     * 登录成功处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{success:true,message:'登入成功'}");
            out.flush();
            out.close();
        }
        return false;
    }

    /**
     * 登录失败处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{success:false,message:'密码错误'}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{success:false,message:'账号不存在'}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{success:false,message:'账号被锁定'}");
            } else {
                out.println("{success:false,message:'未知错误'}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            logger.error(e1.getMessage(), e1.getCause());
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
}
