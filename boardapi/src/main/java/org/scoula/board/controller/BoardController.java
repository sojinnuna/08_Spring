package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 모든 메소드에 @ResposeBody 추가해줌 -> 응답을 json으로 받아온다
@RequestMapping("/api/board") // 메소드들의 공통 url
@RequiredArgsConstructor // final이 붙은 필드로 생성자 만들어줌
@Slf4j // 로깅을 위한 어노테이션
public class BoardController {

    private final BoardService service;

//    http://localhost:8080/api/board
    @GetMapping("") // 빈 객체면 생략 가능함
    public ResponseEntity<List<BoardDTO>> getList() {
//         상태 코드가 200이고 body 타입이 List<BoardDTO>인 응답 객체를 리턴
        return ResponseEntity.ok(service.getList());
    }

//    http://localhost:8080/api/board/1
//    특정 게시글 조회
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
//        상태 코드가 200이고 body 타입이 BoardDTO인 응답 객체를 리턴
        return ResponseEntity.ok(service.get(no));
    }

//    http://localhost:8080/api/board
//    새 게시글 생성
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO board) {
        return ResponseEntity.ok(service.create(board));
    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO board) {
//        no 번호의 게시글을 @RequestBody에 담아온 데이터로 수정
//        mapper.xml 부분의 #{ } 내에 있는 필드 값은 꼭 @RequestBody에 추가해줄 것
//        소문자로 시작하는 primitive 타입을 null 값이 들어가면 예외 발생하기 때문에
//        되도록이면 대문자로 시작하는 wrapper 객체 사용 권장 (int -> Long)
//        PathVariable 변수 이름이 경로와 다를 경우 @pathVariable("no") Long id 와 같이 이름 명시 필요
        return ResponseEntity.ok(service.update(board));
    }

//    기존 게시글 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) { // primitive type
//        return ResponseEntity.ok(service.delete(no));
        service.delete(no);
        return ResponseEntity.ok().build();
    }
}

