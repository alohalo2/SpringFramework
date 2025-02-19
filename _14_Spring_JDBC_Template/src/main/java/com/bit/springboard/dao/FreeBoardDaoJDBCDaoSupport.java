package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// JDBC Template 사용방식 1
// JdbcDaoSupport 클래스를 상속받아 사용하는 방식
@Repository
public class FreeBoardDaoJDBCDaoSupport extends JdbcDaoSupport {

    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    // 게시글 등록 쿼리
    private final String POST = "INSERT INTO FREEBOARD(TITLE, CONTENT, WRITER_ID) VALUES(?, ?, ?)";
    // 게시글 수정 쿼리
    private final String MODIFY = "UPDATE FREEBOARD" +
            "                         SET " +
            "                              TITLE = ?," +
            "                              CONTENT = ?," +
            "                              MODDATE = ?" +
            "                          WHERE ID = ?";

    private final String GET_BOARD_LIST = "SELECT F.ID" +
            "                                   , F.TITLE" +
            "                                   , F.CONTENT" +
            "                                   , F.WRITER_ID" +
            "                                   , M.NICKNAME" +
            "                                   , F.REGDATE" +
            "                                   , F.MODDATE" +
            "                                   , F.CNT" +
            "                                  FROM FREEBOARD F" +
            "                                  JOIN MEMBER M" +
            "                                    ON F.WRITER_ID = M.ID";

    // 게시글 삭제
    private final String DELETE = "DELETE FROM FREEBOARD" +
            "                           WHERE ID = ?";

    // 특정 id의 게시글 하나만 조회
    private final String GET_BOARD = "SELECT F.ID" +
            "                                   , F.TITLE" +
            "                                   , F.CONTENT" +
            "                                   , F.WRITER_ID" +
            "                                   , M.NICKNAME" +
            "                                   , F.REGDATE" +
            "                                   , F.MODDATE" +
            "                                   , F.CNT" +
            "                                  FROM FREEBOARD F" +
            "                                  JOIN MEMBER M" +
            "                                    ON F.WRITER_ID = M.ID" +
            "                                  WHERE F.ID = ?";

    public void post(BoardDto boardDto) {
        System.out.println("FreeBoardDao의 post 메소드 실행");

        getJdbcTemplate().update(POST, boardDto.getTitle(), boardDto.getContent(), boardDto.getWRITER_ID());

        System.out.println("FreeBoardDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("FreeBoardDao의 modify 메소드 실행");

        getJdbcTemplate().update(MODIFY, boardDto.getTitle(), boardDto.getContent(), boardDto.getModdate().toString(), boardDto.getId());

        System.out.println("FreeBoardDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getBoardList() {
        System.out.println("FreeBoardDao의 getBoardList 메소드 실행");

        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDtoList = getJdbcTemplate().query(GET_BOARD_LIST,  new BoardRowMapper());

        System.out.println("FreeBoardDao의 getBoardList 메소드 실행 종료");
        return boardDtoList;
    }

    public void delete(int id) {
        System.out.println("FreeBoardDao의 delete 메소드 실행");

        getJdbcTemplate().update(DELETE, id);

        System.out.println("FreeBoardDao의 delete 메소드 실행 종료");
    }
    
    public BoardDto getBoard(int id) {
        System.out.println("FreeBoardDao의 getBoard 메소드 실행");

        BoardDto boardDto = new BoardDto();

        // queryForObject의 두 번째 매개변수는 Object 배열 형태여야 한다,
        Object[] args = {id};

        boardDto = getJdbcTemplate().queryForObject(GET_BOARD, args, new BoardRowMapper());

        System.out.println("FreeBoardDao의 getBoard 메소드 실행 종료");
        return boardDto;
    }
}
