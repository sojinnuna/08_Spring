package org.scoula.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;


public class JsonResponse { // 200 응답
    // 일반적인 JSON 응답을 보내는 메소드
    public static <T> void send(HttpServletResponse response, T result) throws IOException {
        ObjectMapper om = new ObjectMapper(); // JSON 변환에 필요
        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter(); // 응답에 쓰기 위한 Writer 객체 가져오기
        out.write(om.writeValueAsString(result)); // 객체를 JSON 문자열로 변환해서 응답 작성(직렬화)
        out.flush();
    }
    public static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value()); // HTTP 상태 코드 설정
        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(message);
        out.flush();
    }
}

