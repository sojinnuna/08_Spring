package org.scoula.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Log4j
@RequestMapping("/security")
@Controller
public class SecurityController {
        @GetMapping("/all") // 모두 접근 가능
        public void doAll() {
            log.info("do all can access everybody");
        }

//        name 추출하는 방법(Principle 사용)
//        @GetMapping("/member") // MEMBER 또는 ADMIN 권한 필요
//        public void doMember() {
//            log.info("logined member");
//        }

        @GetMapping("/admin") // ADMIN 권한 필요
        public void doAdmin() {
            log.info("admin only");
        }

        // login page 연걸
        @GetMapping("/login")
        public void login(){
            log.info("login page");
        }

        @GetMapping("/logout")
        public void logout() {
            log.info("logout page");
        }

        @GetMapping("/member")
        public void doMember(Principal principal) {
            log.info("username = " + principal.getName());
        }


}

