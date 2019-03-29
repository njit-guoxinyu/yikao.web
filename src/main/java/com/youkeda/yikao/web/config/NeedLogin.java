package com.youkeda.yikao.web.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NeedLogin{

}
//需不需要登陆
// 用户必须先登录才能访问一些需要登录的服务
//如果没有登录则就转到登录页面