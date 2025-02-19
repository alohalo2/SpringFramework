package com.bit.springboard.service;

import com.bit.springboard.dto.MemberDto;

import java.util.List;

public interface MemberService {
    void join(MemberDto memberDto);

    List<MemberDto> getMembers();

    MemberDto getMemberByUsername(String username);

}
