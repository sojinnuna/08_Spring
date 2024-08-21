package org.scoula.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice // 해당 클래스에서 예외를 처리하겠다
@Log4j
public class CommonExceptionAdvice {
    // 모든 예외에 대한 처리
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        log.error("Exception ........." + e.getMessage());
        model.addAttribute("exception", e); // 예외 객체 모델에 추가해서 뷰에 전달
        log.error(e);
        return "error_page"; // 뷰 이름 반환
    }

    //    NoHandlerFoundException 예외를 처리해주겠다
    @ExceptionHandler(NoHandlerFoundException.class)
//    HTTP 상태 코드를 404로 설정하겠다
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e){
        return "custom404";
    }
}