package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class FreeBoardServiceRun {
    public static void main(String[] args) {

        // Spring Container 구동
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        // 어노테이션 옆에 아이디를 붙일 경우에는 아이디로 호출하지만 안붙일 경우에는
        // 클래스 명에서 앞 글자만 소문자로 변경된 값이 아이디 기본값으로 설정됨
        BoardService boardService = (BoardService) factory.getBean("freeBoardServiceImpl");

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("자유게시글");
        boardDto.setContent("자유게시글 2번 입니다.");
        // writer_id는 member 테이블의 id 컬럼을 foreign key로 가져오기 때문에
        // member 테이블에 존재하는 id 값만 입력할 수 있다.
        boardDto.setWRITER_ID(2);

        boardService.post(boardDto);

        // 게시글 수정
        BoardDto modifyBoardDto = new BoardDto();

        modifyBoardDto.setId(1);
        modifyBoardDto.setTitle("자유게시글1 수정");
        modifyBoardDto.setContent("자유게시글 1번입니다.-수정됨");
        modifyBoardDto.setModdate(LocalDateTime.now());

        boardService.modify(modifyBoardDto);

        // 게시글 삭제
        boardService.delete(3);

        // 게시글 목록 조회
        boardService.getBoardList().forEach(board -> {
            System.out.println(board);
        });

        // 특정 id의 게시글 하나만 조회
        System.out.println(boardService.getBoard(5));

        factory.close();
    }
}
