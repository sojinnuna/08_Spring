package org.scoula.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.xml.stream.Location;

@Slf4j
@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;
    final long MAX_REQUEST_SIZE = 1024*1024*20L;
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;


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

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
//        해당하는 핸들러를 찾을 수 없을 때 예외를 발생시켜준다 (404)
        registration.setInitParameter("throwExceptionIfNoHandelerFound", "true");

        // 파일 업로드 설정
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                LOCATION, // 업로드된 파일이 저장될 디렉토리 경로
                MAX_FILE_SIZE, // 업로드 가능한 파일 하나의 최대 크기
                MAX_REQUEST_SIZE, // 업로드 가능한 전체 파일의 최대 크기
                FILE_SIZE_THRESHOLD // 메모리 제한, 이보다 더 작은 파일은 메모리에서만 처리
        );
        registration.setMultipartConfig(multipartConfig); // 설정한 MultipartConfigElement 등록
    }
}
