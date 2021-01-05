package com.atxiaoming.config;

import com.atxiaoming.handler.AuthHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration  //告诉springboot 这是一个配置类 相当于applicationMvc.xml
public class ItsourceWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthHandlerInterceptor authHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*
                /*与/**的区别：
                     /*:是拦截所有的请求，你的请求只有一级   比如:/a /b  /c  /d  都是能拦截的，如果你的请求是多级  如:/a/b  /a/b/c /a/b/c/d  统一都不能拦截

                     /**: 是拦截所有的请求，你的请求是一级或者多级甚至n级我都是能拦截的
                            比如：/a  /a/b   /a/b/c  /a/b/c/d.... 都是能拦截的

         */
        //告诉springboot 我配置了一个拦截器
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/role/**")
                .addPathPatterns("/user/**")
                .addPathPatterns("/menu/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/checkLogin")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/swagger-ui.html/**");
    }
}
