package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FreeBoardDao {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // 게시글 등록 쿼리
    private final String post = "insert into FreeBoard(TITLE, CONTENT, WRITER_ID) values(?,?,?)";
    // 게시글 수정 쿼리
    private final String modify = "UPDATE FREEBOARD" +
            "                           SET TITLE = ?," +
            "                               CONTENT = ?," +
            "                               MODDATE = ?" +
            "                           WHERE ID = ?";

    private final String GET_BOARD_LIST = "SELECT F.ID" +
            "                                   , F.TITLE" +
            "                                   , F.CONTENT" +
            "                                   , F.WRITER_ID" +
            "                                   , M.NICKNAME" +
            "                                   , F.REGDATE" +
            "                                   , F.MODDATE" +
            "                                   , F.CNT" +
            "                                   FROM FREEBOARD F" +
            "                                   JOIN MEMBER M" +
            "                                     ON F.WRITER_ID = M.ID";

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
            "                                   FROM FREEBOARD F" +
            "                                   JOIN MEMBER M" +
            "                                     ON F.WRITER_ID = M.ID" +
            "                                   WHERE F.ID = ?";

    public void post(BoardDto boardDto) {
        System.out.println("FreeBoradDao의 post 메소드 실행");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(post);

            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setInt(3, boardDto.getWRITER_ID());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("FreeBoardDao의 post 메소드 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("FreeBoardDao의 modify 메소드 실행");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(modify);

            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setString(3, String.valueOf(boardDto.getModdate()));
            stmt.setInt(4,boardDto.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("FreeBoardDao의 modify 메소드 종료");
    }


//    public BoardDto modify(BoardDto boardDto) {
//
//        System.out.println("FreeBoardDao의 modify 메소드 실행");
//
//        BoardDto returnBoardDto = new BoardDto();
//
//        try {
//
//            conn = JDBCUtil.getConnection();
//
//            stmt = conn.prepareStatement(modify);
//
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                returnBoardDto.setTitle(rs.getString("TITLE"));
//                returnBoardDto.setContent(rs.getString("CONTENT"));
//                // UPDATE문은 resultSet으로 작동 불가능
//                returnBoardDto.setModdate(rs.getDate("MODDATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            JDBCUtil.close(conn, stmt, rs);
//        }
//        System.out.println("FreeBoardDao의 modify 메소드 종료");
//        return returnBoardDto;
//    }


    public List<BoardDto> getBoardList() {
        System.out.println("FreeBoardDao의 getBoardList 메소드 실행");

        List<BoardDto> boardDtoList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD_LIST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardDto boardDto = new BoardDto();

                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                // java.sql.Date 타입  -> LocalDateTime으로 변환
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));

                boardDtoList.add(boardDto);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("FreeBoardDao의 getBoardList 메소드 종료");
        return boardDtoList;
    }

    public void delete(int id) {
        System.out.println("FreeBoardDao의 delete 메소드 실행");

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(DELETE);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("FreeBoardDao의 delete 메소드 종료");
    }

    public BoardDto getBoard(int id) {
        System.out.println("FreeBoardDao의 getBoard 메소드 실행");

        BoardDto boardDto = new BoardDto();

        try {

            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                boardDto.setId(rs.getInt(id));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("FreeBoardDao의 getBoard 메소드 종료");
        return boardDto;
    }

}
