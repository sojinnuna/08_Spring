package org.scoula.security.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String username;
    private String password;

    public static LoginDTO of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper 생성
        try {
            // request에서 JSON 데이터를 읽어와서 LoginDTO 객체로 변환
            return om.readValue(request.getInputStream(), LoginDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
//            자격 증명이 잘못되었을 때 나타나는 예외
            throw new BadCredentialsException("username 또는 password가 없습니다.");
        }
    }
}

