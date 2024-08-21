package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j
@Service // Service 역할을 하는 Bean 등록
@RequiredArgsConstructor // final 필드로 생성자 추가
public class BoardServiceImpl implements BoardService {
//    생성자가 하나 있다면 그 생성자로 주입 가능
private final static String BASE_DIR = "c:/upload/board";
final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList.............");

        return mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // 전부 BoardDTO로 변환 -> BoardDTO의 스트림
                .toList(); // BoardDTD의 리스트로 변환
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);
        BoardDTO board = BoardDTO.of(mapper.get(no));
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new);
    }

    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
// RuntimeException인 경우만 자동 rollback.
    @Transactional
    @Override
    public BoardDTO create(BoardDTO board) {
        log.info("create......" + board);

//        DTO를 VO로 변경해서 mapper의 메소드 호출
        BoardVO boardVO = board.toVo();
        mapper.create(boardVO);

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) { // 첨부 파일이 있는 경우
            upload(boardVO.getNo(), files);
        }
        return get(boardVO.getNo());
    }

    @Override
    public BoardDTO update(BoardDTO board) {
        log.info("update......" + board);
//        mapper의 업데이트를 호출해서 수정된 행의 수가 1일 경우 true반환
        mapper.update(board.toVo());

        return get(board.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete...." + no);
        BoardDTO board = get(no);

        mapper.delete(no);
        return board;
    }


//    해당 게시물에 참조 파일들을 참조해주는 메서드
    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part: files) {
//            첨부파일 목록에서 파일을 하나씩 꺼내서 비어있는지 확인
//            비어있으면 다음 파일 확인
            if (part.isEmpty()) continue;
            try {
//                업로드 경로 생성 후 BoardAttachmentVO 객체 생성
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
//                BoardAttachment 테이블에 참조파일 데이터 하나 추가
                mapper.createAttachment(attach);
            } catch (IOException e) {
                throw new RuntimeException(e);   // @Transactional에서감지, 자동rollback
            }
        }
    }
        // 첨부파일 한 개 얻기
        @Override
        public BoardAttachmentVO getAttachment (Long no){
            return mapper.getAttachment(no);
        }
        // 첨부파일 삭제
        @Override
        public boolean deleteAttachment (Long no){
            return mapper.deleteAttachment(no) == 1;
        }
    }

