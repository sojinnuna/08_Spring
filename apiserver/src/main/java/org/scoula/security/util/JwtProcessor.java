package org.scoula.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProcessor {
    static private final long TOKEN_VALID_MILISECOND = 1000L * 60 * 5; // 5 분

//    비밀 키 설정 - 개발시에는 서버 재기동하는 경우가 많으므로 임의의 문자열
    private String secretKey = "sfsanfjDSAgafsafdasdfasFDSFDSAfdsfdasfdsfAFDf";

    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//    비밀키 설정 - 매번 변경되므로 운영시에만 사용
//    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); -- 운영시 사용

    // JWT 토큰 생성 메서드
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject) // 주로 사용자 식별용
                .setIssuedAt(new Date()) // JWT 발급 시간
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILISECOND))
                .signWith(key) // JWT 서명을 위한 키 설정
                .compact(); // JWT 문자열 생성 및 반환
    }

    // JWT Subject(username) 추출 - 해석 불가인 경우 예외 발생
    // 예외 ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
    // IllegalArgumentException
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // JWT 검증(유효 기간 검증) - 해석 불가인 경우 예외 발생
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key) // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token); // 토큰 파싱 및 서명 검증
        return true; // 유효한 토큰일 경우 true 반환
    }
}
