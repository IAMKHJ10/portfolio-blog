package com.portfolio.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 적힌곳만 인터셉터 작동
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/post/delete");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
