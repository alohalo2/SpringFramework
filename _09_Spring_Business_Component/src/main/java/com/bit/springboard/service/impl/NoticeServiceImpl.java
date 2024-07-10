package com.bit.springboard.service.impl;

import com.bit.springboard.dto.NoticeDto;
import com.bit.springboard.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeDto noticeDto;


    @Override
    public void post(NoticeDto noticeDto) {

    }

    @Override
    public void modify(NoticeDto noticeDto) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<NoticeDto> getBoardList() {
        return List.of();
    }

    @Override
    public NoticeDto getBoard(int id) {
        return null;
    }
}
