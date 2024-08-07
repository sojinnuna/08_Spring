package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class BoardMapperTest {
    @Autowired
    private BoardMapper mapper;

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    public void getList() {
        for (BoardVO board : mapper.getList()) {
            log.info(board);
        }
    }

    // get(select) 처리
    @Test
    @DisplayName("BoardMapper의게시글읽기")
    public void get() {
        // 존재하는게시물번호로테스트
        BoardVO board = mapper.get(1L);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의 새글 작성")
    public void create() {
        BoardVO board = new BoardVO();
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는내용");
        board.setWriter("user0");
        mapper.create(board);
        log.info(board);
    }

}
