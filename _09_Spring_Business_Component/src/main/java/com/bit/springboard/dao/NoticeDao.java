package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.NoticeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NoticeDao {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // 알림 등록 쿼리
    private final String post = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?)";

    // 알림 수정 쿼리
    private final String modify = "UPDATE NOTICE" +
            "                             SET TITLE = ?" +
            "                               , CONTENT = ?" +
            "                               , WRITER_ID = ?" +
            "                               WHERE ID = ?";

    // 알림 삭제 쿼리
    private final String delete = "DELETE";

    public void post(NoticeDto noticeDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(post);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }




        System.out.println("NoticeDao의 post 메소드 종료");
    }

}
