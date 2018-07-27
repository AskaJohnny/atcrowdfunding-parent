package com.johnny.atcrowdfunding.config;

import com.johnny.atcrowdfunding.component.AppPathListener;
import com.johnny.atcrowdfunding.component.LoginIntercepter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.reflect.generics.visitor.Reifier;

/**
 * ]
 *
 * @author johnny
 * @create 2018-07-18 下午1:19
 **/
@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/", "/webjars/**", "/login/login", "/bootstrap/**", "/css/**", "/fonts/**", "/jquery/**", "/script/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        //给后台页面添加一个
        registry.addViewController("/main").setViewName("main");
    }


//    @Bean
//    public ServletListenerRegistrationBean<AppPathListener> appPathListener() {
//        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
//        registrationBean.setListener(new AppPathListener());
//        return registrationBean;
//
//    }

}