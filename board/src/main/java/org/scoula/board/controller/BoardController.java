package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board") // url 공통주소 할당
@RequiredArgsConstructor // 생성자 주입
public class BoardController {

    final private BoardService service;

    // return 값이 void기 때문에 뷰도 경로가 동일(board/list)
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
//        Model의 속성에 데이터를 담을 경우 뷰로 전달 가능
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/create")
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardDTO board,
                         RedirectAttributes ra) {
        service.create(board);
        ra.addFlashAttribute("result", board.getNo());
        return "redirect:/board/list";
    }


    @GetMapping({"/get", "/update"}) // "/get"과 "/update" 경로를 둘 다 처리
//    @RequestParam: 주소 뒤에 ?를 붙여서 쿼리스트링으로 정보를 받아준다
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board,
                         RedirectAttributes ra) {
        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no, RedirectAttributes ra) {
        if (service.delete(no)) {

            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";

    }
}

