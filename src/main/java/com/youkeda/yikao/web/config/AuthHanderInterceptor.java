package com.youkeda.yikao.web.config;

import com.youkeda.yikao.web.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加一个自定义拦截器，实现HandlerInterceptor接口
@Component  //把当前的类交给Spring管理，会自动做new的实例化
public class AuthHanderInterceptor implements HandlerInterceptor {

    //在每个方法运行之前，执行拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod =(HandlerMethod) handler;
            //如果handle属于HandlerMethod，做一下类型转换
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
            //取出方法的needLogin注解,如果有注解的话，说明该方法需要登录
            if (needLogin!=null){
                User user = (User) request.getSession().getAttribute("user");
                //如果有注解，就取出用户信息
                if (user==null){
                    response.sendRedirect("/login.html");
                    return false;
                    //如果用户不存在，但此时又需要用户存在，就返回登陆页面
                }
            }
        }
        return true;
    }
}
