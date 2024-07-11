package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.NoticeDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
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
            "                               , MODDATE = ?" +
            "                               WHERE ID = ?";

    // 알림 List 쿼리
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

    // 알림 삭제 쿼리
    private final String delete = "DELETE FROM NOTICE" +
            "                             WHERE ID = ?";

    // 알림 List 쿼리
    private final String getNotice = "SELECT N.ID" +
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
            "                                   WHERE N.ID = ?";

    public void post(NoticeDto noticeDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(post);

            stmt.setString(1,noticeDto.getTitle());
            stmt.setString(2,noticeDto.getContent());
            stmt.setInt(3,noticeDto.getWRIETER_ID());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("NoticeDao의 post 메소드 종료");
    }

    public void modify(NoticeDto noticeDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(modify);

            stmt.setString(1,noticeDto.getTitle());
            stmt.setString(2,noticeDto.getContent());
            stmt.setString(3, String.valueOf(noticeDto.getModdate()));
            stmt.setInt(4,noticeDto.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao의 modify 메소드 종료");
    }

    public List<NoticeDto> getNoticeList() {
        System.out.println("NoticeDao의 getNoticeList 메소드 실행");

        List<NoticeDto> returnNoticeDtoList = new ArrayList<>();

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_NOTICE_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                NoticeDto noticeDto = new NoticeDto();
                noticeDto.setId(rs.getInt("ID"));
                noticeDto.setTitle(rs.getString("TITLE"));
                noticeDto.setContent(rs.getString("CONTENT"));
                noticeDto.setWRIETER_ID(rs.getInt("WRITER_ID"));
                // Nickname 넣어야함
                noticeDto.setNickname(rs.getString("NICKNAME"));
                noticeDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                noticeDto.setCnt(rs.getInt("CNT"));

                returnNoticeDtoList.add(noticeDto);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("NoticeDao의 getNoticeList 메소드 종료");
        return returnNoticeDtoList;
    }

    public void delete(int id) {
        System.out.println("NoticeDao의 delete 메소드 실행");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(delete);

            stmt.setInt(1,id);

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("NoticeDao의 delete 메소드 종료");
    }

    public NoticeDto getNotice(int id) {
        System.out.println("NoticeDao의 delete 메소드 실행");

        NoticeDto returnNoticeDto = new NoticeDto();

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(getNotice);

            stmt.setInt(1,id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnNoticeDto.setId(rs.getInt("id"));
                returnNoticeDto.setTitle(rs.getString("TITLE"));
                returnNoticeDto.setContent(rs.getString("CONTENT"));
                returnNoticeDto.setWRIETER_ID(rs.getInt("WRITER_ID"));
                returnNoticeDto.setNickname(rs.getString("NICKNAME"));
                returnNoticeDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                returnNoticeDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                returnNoticeDto.setCnt(rs.getInt("CNT"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        System.out.println("NoticeDao의 delete 메소드 종료");
        return returnNoticeDto;
    }

}
