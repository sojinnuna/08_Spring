package org.scoula.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// @Configuration: 설정
@Configuration
@Data // 기본생성자, Getter+Setter
@RequiredArgsConstructor // final이 붙은 필드를 무조건 생성자에 넣겠다
public class Restaurant {
    // Chef가 빈으로 등록되어있어야 함
    // @Autowired // 외부에서 의존성 주입해서 자동 연결하겠다
    final private Chef chef;

    // 생성자가 1개인 경우에만 지원됨
    // @Autowired
    // Restaurant(Chef chef)
}
