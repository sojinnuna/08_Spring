package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.rmi.registry.Registry;

//@EnableWebMvc: mvc 패턴(frontcontroller 패턴) 사용하겠다
@EnableWebMvc
//controller 패키지 내부에서 컴포넌트를 찾아라
@ComponentScan(basePackages = {"org.scoula.controller"})
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/resources/**") // url이 /resources/로 시작하는 경로
                .addResourceLocations("/resources"); // webapp/resources/ 경로
    }

// jsp view resolver 설정
@Override
public void configureViewResolvers(ViewResolverRegistry registry){
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/views/");
    bean.setSuffix(".jsp");
    registry.viewResolver(bean);
}
}