package org.scoula.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@Log4j
public class CommonExceptionAdvice {
// 모든 예외에 대한 처리
    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model){
        log.error("Exception ......" + ex.getMessage());
        model.addAttribute("exception", ex); // 예외 객체 모델에 추가해서 뷰에 전달
        log.error(model);
        return "error_page"; // 뷰 이름 반환
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex){
        return "custom404";
    }
}
