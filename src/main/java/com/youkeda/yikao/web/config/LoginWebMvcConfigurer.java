package com.youkeda.yikao.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //一般拦截器的注册配置用这个，Configurer就用这个注解
public class LoginWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthHanderInterceptor authHanderInterceptor;
    //通过依赖注入，获得刚才创建的登录拦截器

    //复写一下添加的方法，注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHanderInterceptor).addPathPatterns("/**");
        //add：拦截器加载，还要加上映射规则，是正则方式匹配路径(拦截所有请求，都得走拦截器逻辑)
    }
}
