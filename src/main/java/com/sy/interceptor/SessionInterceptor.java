package com.sy.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sy.model.Result;
import com.sy.util.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SessionInterceptor
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 16:30
 * @Version 1.0
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //客户访问的资源uri
        String requestURI = request.getRequestURI();
        if(Constant.URI_LOGIN.equals(requestURI)){
            return true;
        }else{
            Object user = request.getSession().getAttribute(Constant.SESSION_USER);
            if(user!=null){
                return true;
            }else{
                Result result = new Result();
                result.respError();
                result.setMsg("No Permission!! ");
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(result);
                response.getWriter().write(json);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
