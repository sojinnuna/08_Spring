package org.scoula.security.util;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class })
@Log4j

class JwtProcessorTest {
    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token); // 토큰이 null이 아닌지 확인하여 테스트 검증
    }

    @Test
    void getUsername() {
//        JWT 토큰에서 사용자 이름 추출하는 테스트
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTcyNDIyNDUwNywiZXhwIjoxNzI0MjI0ODA3fQ.ae64r4Loj5xYydb7Pc9ncmMN59luyDaUcVDsCz0k974";

        String username = jwtProcessor.getUsername(token); // JWT 토큰에서 사용자 이름 추출
        log.info(username);
        assertNotNull(username); // 사용자 이름이 null이 아닌지 확인
    }
    @Test
    void validateToken(){
        // 5분 경과 후 테스트
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTcyNDIyNDUwNywiZXhwIjoxNzI0MjI0ODA3fQ.ae64r4Loj5xYydb7Pc9ncmMN59luyDaUcVDsCz0k974";

        boolean isValid = jwtProcessor.validateToken(token); // 5분 경과 후면 예외 발생
        log.info(isValid);
        assertTrue(isValid); // 5분전이면 true
    }
}