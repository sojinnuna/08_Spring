package org.scoula.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.dto.SampleDTO;
import org.scoula.dto.TodoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller // Bean 등록 + Controller 명시
@RequestMapping("/sample") // 해당 클래스에 있는 메소드는 해당 url로 매핑하겠다
@Log4j
public class SampleController {

    @RequestMapping("") // url: /sample
    public void basic() {
        log.info("basic.........");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST}) // url: /sample/basic
    public void basicGet() {
        log.info("basic get..........");
    }

    //    @RequestMapping + Get 요청을 합친 어노테이션, GET 요청만 처리 가능
    @GetMapping("/basicOnlyGet") // url: /sample/basicOnlyGet
    public void basicGet2() {
        log.info("basic get only get..........");
    }

    // localhost:8080/sample/ex01
    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
//        넣어준 값이 없을 경우 초기값으로 나온다.
        log.info("" + dto);

        return "ex01"; // 뷰의 이름 반환
    }

    //    @RequestParam은 DTO 객체를 안쓰고 각각 파라미터를 받아줄때 사용
//    필드 두개 다 제대로 안 넣어주면 예외 발생, 개별 파라미터는 뷰로 전달 불가능
    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,
            @RequestParam("age") int age) {
        log.info("name: " + name);
        log.info("age " + age);

        return "ex02";
    }

    //    같은 이름으로 여러번 전달하는 경우 List나 배열로 받아올 수 있다
//    localhost:8080/sample/ex02List
    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
    }

//    @GetMapping("ex02Bean")
//    public String ex02Bean(SampleDTOList list){
//        log.info("list: " + list);
//        return "ex02Bean";
//    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
    }

//     http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
//    page를 기본 자료형으로 넘기면 뷰로 전달되지 않는다
//    따라서 @ModelAttribute로 전달해야 한다( request scope 저장)
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
    }

//     http://localhost:8080/sample/ex05
//    return 값이 void일 경우 요청 url을 기준으로 jsp 파일을 찾는다
//    /WEB-INF/views/sample/ex05.jsp 파일을 찾게 됨
    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05.........");
    }

//     http://localhost:8080/sample/ex06
//     http://localhost:8080/sample/ex06-2?name=AAA&age=10로 리다이렉트함
    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06.........");
//        리다이렉트 시 요청 파라미터로 name과 age를 추가해준다
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
//        리다이렉트 시 "redirect:" 접두사를 사용한다
        return "redirect:/sample/ex06-2";
    }

//     http://localhost:8080/sample/ex07
//    @ResponseBody 어노테이션은 반환된 객체가 JSON 형식으로 변환되어 보여지도록 한다
    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07(){
        log.info("/ex07.......");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");

        return dto;
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08(){
        log.info("/ex08.......");

        // {"name": "홍길동"}
        String msg = "{\"name\": \"홍길동\"}"; // body에 들어갈 json 형태의 문자열

        HttpHeaders header = new HttpHeaders();
//        HttpHeaders 객체 생성 후 Content-Type 헤더 설정
//        키, 값을 넣어줌
        header.add("Content-Type", "application/json;charset=UTF-8");
//        body. head, 상태
//        ResponseEntity 객체 내에 바디, 헤더, 생성 코드(200) 반환
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping
    public void exUpload(){ // 리턴 타입이 void인 경우, view 이름은 sample/exUpload 임
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        for(MultipartFile file: files){
            log.info("------------------------------");
            log.info("name: " + file.getOriginalFilename());
            log.info("size: " + file.getSize());
        }
    }
}
