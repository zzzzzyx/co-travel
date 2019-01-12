package cn.nju.agile.travel.config;

import cn.nju.agile.travel.config.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Autowired
    AccessInterceptor accessInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
    }
    
}

