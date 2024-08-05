package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

//    RootConfig 클래스를 뭐로 할건지 반환
    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class[] {RootConfig.class};
    }

//    ServletConfig 클래스를 뭐로 할건지 반환
    @Override
    protected  Class<?>[] getServletConfigClasses(){
        return new Class[] {ServletConfig.class};
    }

//    DispatchConfig이 매핑할 URL 패턴
    @Override
    protected String[] getServletMappings(){
        return new String[] {"/"};
    }

    // POST body 문자 인코딩 필터 설정 - UTF-8
//    UTR-8 인코딩을 강제로 사용하도록 하는 필터 반환
    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] {characterEncodingFilter};
    }
}
