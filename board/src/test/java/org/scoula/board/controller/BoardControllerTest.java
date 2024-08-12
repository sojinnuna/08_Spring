package org.scoula.board.controller;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.Queue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration // MockMvc를 사용하기 위해 필요
@ContextConfiguration(classes ={RootConfig.class,ServletConfig.class}) // Spring 컨테이너 설정
@Log4j
public class BoardControllerTest {

    @Autowired
    BoardService service;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach // 각 테스트 메소드 실행 전에 호출
    public void setup() {
//        mockMvc 객체를 초기화
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void list() throws Exception {
        log.info(
//                borar/list을 처리하는 controller의 list() 메소드 호출
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) // ResultActions 리턴
                        .andReturn() // MvcResult 리턴
                        .getModelAndView() // ModelAndView 리턴
                        .getModelMap() // Model 리턴
        );
    }

    @Test
    public void create() throws Exception {
        String resultPage = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/board/create")
                                .param("title", "테스트 새글 제목")
                                .param("content", "테스트 새글 내용")
                                .param("writer", "user1"))
                .andReturn()
                .getModelAndView()
                .getViewName(); // 해당하는 뷰의 이름 return

//        post로 연결했기 때문에 리스트 페이지 리다이렉트 됨
//        redirect:/board/list
        log.info(resultPage);
    }

    @Test
    public void get() throws Exception {
        // param 부분에 해당 주소 값을 받아올 때 필요한 파라미터 값을 넘겨준다
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("no", "1"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }

    @Test
    public void update() throws Exception {
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/update")
                                .param("no", "1")
                                .param("title", "수정된 테스트 새글 제목")
                                .param("content", "수정된 테스트 새글 내용")
                                .param("writer", "user00"))
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);
    }

    @Test
    public void delete() throws Exception {
        // 삭제 전 데이터베이스에 게시물 번호 확인할 것
        String resultPage= mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/board/delete")
                                .param("no", "25") )
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }
}



