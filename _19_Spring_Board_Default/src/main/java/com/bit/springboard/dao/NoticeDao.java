package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeDao {
    private SqlSessionTemplate mybatis;

    @Autowired
    public void NoticeDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        mybatis.insert("NoticeDao.post", boardDto);

        System.out.println("NoticeDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");

        mybatis.update("NoticeDao.modify", boardDto);

        System.out.println("NoticeDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getNoticeList(Map<String, String> searchMap) {
        System.out.println("NoticeDao의 getNoticeList 메소드 실행");

        List<BoardDto> noticeList = new ArrayList<>();

        noticeList = mybatis.selectList("NoticeDao.getNoticeList", searchMap);

        System.out.println("NoticeDao의 getNoticeList 메소드 실행 종료");
        return noticeList;
    }
    
    public void delete(int id) {
        System.out.println("NoticeDao의 delete 메소드 실행");

        mybatis.delete("NoticeDao.delete", id);

        System.out.println("NoticeDao의 delete 메소드 실행 종료");
    }

    public BoardDto getNotice(int id) {
        System.out.println("NoticeDao의 getNotice 메소드 실행");

        BoardDto boardDto = new BoardDto();

        boardDto = mybatis.selectOne("NoticeDao.getNotice", id);

        System.out.println("NoticeDao의 getNotice 메소드 실행 종료");
        return boardDto;
    }

    public void updateCnt(int id) {
        mybatis.update("NoticeDao.updateCnt", id);
    }
}
