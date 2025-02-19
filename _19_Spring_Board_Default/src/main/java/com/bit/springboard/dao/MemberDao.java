package com.bit.springboard.dao;

import com.bit.springboard.dto.MemberDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// DAO(Data Access Object): 데이터베이스에 직접 접근해서 쿼리를 실행하는 클래스
@Repository
public class MemberDao {
    private SqlSessionTemplate mybatis;


    @Autowired
    public void MemberDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }

    public void join(MemberDto memberDto) {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 join 메소드 실행");

        mybatis.insert("MemberDao.join", memberDto);

        System.out.println("MemberDao의 join 메소드 실행 종료");
    }
    
    public List<MemberDto> getMembers() {
        System.out.println("MemberDao의 getMembers 메소드 실행");

//        List<MemberDto> memberDtoList = new ArrayList<>();
//
//        memberDtoList = mybatis.selectList("MemberDao.getMemberList");
//
//        System.out.println("MemberDao의 getMembers 메소드 실행 종료");
//        return memberDtoList;
        return mybatis.selectList("MemberDao.getMemberList");
    }

    public MemberDto getMemberByUsername(String username) {
        System.out.println("MemberDao의 getMemberByUsername 메소드 실행");

//        MemberDto returnMemberDto = new MemberDto();
//
//        returnMemberDto = mybatis.selectOne("MemberDao.getMember", username);
//
//        System.out.println("MemberDao의 getMemberByUsername 메소드 실행 종료");
//        return returnMemberDto;
        return mybatis.selectOne("MemberDao.getMember", username);

    }

    public int usernameCheck(String username) {
        return mybatis.selectOne("MemberDao.usernameCheck", username);
    }

    public int nicknameCheck(String nickname) {
        return mybatis.selectOne("MemberDao.nicknameCheck", nickname);
    }

    public MemberDto login(MemberDto memberDto) {
        return mybatis.selectOne("MemberDao.login", memberDto);
    }
}
