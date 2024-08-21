package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity // 모든 페이지에서 자동으로 인증을 하도록 설정
@Log4j
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // PasswordEncoder의 구현체
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 경로별 접근 권한 설정
        http.authorizeRequests()
                // '/security/all' 경로는 모든 사용자에게 접근 허용
                .antMatchers("/security/all").permitAll()
                // '/security/admin' 경로는 ADMIN 권한이 있는 사용자만 접근 가능
                .antMatchers("/security/admin").access("hasRole('ADMIN')")
                // '/security/member' 경로는 MEMBER 권한이 있는 사용자만 접근 가능
                .antMatchers("/security/member").access("hasRole('MEMBER')");

        // form 기반 로그인 활성화, 나머지 설정은 디폴트값 사용
        http.formLogin()
                .loginPage("/security/login")  // 로그인 페이지 GET 요청시
                .loginProcessingUrl("/security/login") // 로그인 페이지의 form에서 제출 시 (action)
                .defaultSuccessUrl("/"); //로그인 성공시 리다이렉트 페이지

        http.logout()
                .logoutUrl("/security/logout") // POST 요청시 호출할 url
                .invalidateHttpSession(true) // 세션 초기화
                // remeber-me : 브라우저 종료 시 기억하게 하는 기능(따로 설정 필요)
                .deleteCookies("remember-me","JSESSION-ID") // 사용하고 있던 쿠키 삭제
                .logoutSuccessUrl("/security/logout"); // GET 요청시 이동할 페이지

        // addFilterBefore 메서드를 사용하여 CharacterEncodingFilter를 CsrfFilter 이전에 추가
        // 이 설정은 모든 요청에 대해 UTF-8 인코딩 적용 후에 CSRF 보호가 이루어지도록 함
//        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
//        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure ...................................");

//        메모리 정보 대신 UserDetailsService를 사용해서 인증 처리하도록 설정
        auth
                .userDetailsService(userDetailsService) // 사용자 정보 로드
                .passwordEncoder(passwordEncoder()); // 비밀번호 암호화

        // 메모리 내에 인증 설정
        // ROLE_ADMIN
//        auth.inMemoryAuthentication()
//                .withUser("admin") //  사용자 이름 설정
////                .password("{noop}1234")  // 사용자 비밀번호 설정(noop(no operation) : 암호화 미적용)
//                .password("$2a$10$od8KUyLyTf6I0HZYdiRW8Oapc9tsO.kbrB/C7SymXQtX.iBLHhhwa")
//                .roles("ADMIN", "MEMBER"); // ADMIN과 MEMBER 역할 모두 소유

//        ROLE_MEMBER
//        auth.inMemoryAuthentication()
//                .withUser("member")
////                .password("{noop}1234")
//                .password("$2a$10$uzC12yFYGHTEJpsnAOYTQOGHBTlF.zBqBeoAlurNq3K9SF6e8x8Jm")
//                .roles("MEMBER"); // MEMBER 역할만 부여
    }

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

        // UTF-8 인코딩을 설정합니다.
        encodingFilter.setEncoding("UTF-8");
        // 강제로 UTF-8 인코딩을 적용하도록 설정
        encodingFilter.setForceEncoding(true);
        // 생성된 필터를 반환
        return encodingFilter;


    }
}