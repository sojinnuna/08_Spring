package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Bean 등록
@Slf4j // lombok 제공, log() 라는 멤버 변수가 생긴다
public class HomeController {

    @GetMapping("/")
    public String home(){
        log.info("===============> HomeController /");
        return "index"; // View의 이름
    }
}
