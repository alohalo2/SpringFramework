package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDaoJDBCDaoSupport extends JdbcDaoSupport {

    @Autowired
    public void superDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    private final String POST = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?, ?, ?)";

    private final String MODIFY = "UPDATE NOTICE" +
            "                         SET" +
            "                               TITLE = ?," +
            "                               CONTENT = ?," +
            "                               MODDATE = ?" +
            "                         WHERE ID = ?";

    private final String GET_NOTICE_LIST = "SELECT N.ID" +
            "                                    , N.TITLE" +
            "                                    , N.CONTENT" +
            "                                    , N.WRITER_ID" +
            "                                    , M.NICKNAME" +
            "                                    , N.REGDATE" +
            "                                    , N.MODDATE" +
            "                                    , N.CNT" +
            "                                   FROM NOTICE N" +
            "                                   JOIN MEMBER M" +
            "                                     ON N.WRITER_ID = M.ID";

    private final String DELETE = "DELETE FROM NOTICE WHERE ID = ?";

    private final String GET_NOTICE = "SELECT N.ID" +
        "                                    , N.TITLE" +
        "                                    , N.CONTENT" +
        "                                    , N.WRITER_ID" +
        "                                    , M.NICKNAME" +
        "                                    , N.REGDATE" +
        "                                    , N.MODDATE" +
        "                                    , N.CNT" +
        "                                   FROM NOTICE N" +
        "                                   JOIN MEMBER M" +
        "                                     ON N.WRITER_ID = M.ID" +
            "                               WHERE N.ID = ?";

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        getJdbcTemplate().update(POST, boardDto.getTitle(), boardDto.getContent(), boardDto.getWRITER_ID());

        System.out.println("NoticeDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");

        getJdbcTemplate().update(MODIFY, boardDto.getTitle(), boardDto.getContent(), boardDto.getModdate(), boardDto.getId());

        System.out.println("NoticeDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getNoticeList() {
        System.out.println("NoticeDao의 getNoticeList 메소드 실행");

        List<BoardDto> noticeList = new ArrayList<>();

        noticeList = getJdbcTemplate().query(GET_NOTICE_LIST, new BoardRowMapper());

        System.out.println("NoticeDao의 getNoticeList 메소드 실행 종료");
        return noticeList;
    }
    
    public void delete(int id) {
        System.out.println("NoticeDao의 delete 메소드 실행");

        getJdbcTemplate().update(DELETE, id);

        System.out.println("NoticeDao의 delete 메소드 실행 종료");
    }

    public BoardDto getNotice(int id) {
        System.out.println("NoticeDao의 getNotice 메소드 실행");

        BoardDto boardDto = new BoardDto();

        Object[] args = {id};

        boardDto = getJdbcTemplate().queryForObject(GET_NOTICE, args, new BoardRowMapper());


        System.out.println("NoticeDao의 getNotice 메소드 실행 종료");
        return boardDto;
    }
}
