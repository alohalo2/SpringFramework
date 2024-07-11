package com.bit.springboard.service;

import com.bit.springboard.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    void post(NoticeDto noticeDto);

    void modify(NoticeDto noticeDto);

    void delete(int id);

    List<NoticeDto> getNoticeList();

    NoticeDto getNotice(int id);
}
