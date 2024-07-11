package com.bit.springboard.service.impl;

import com.bit.springboard.dao.NoticeDao;
import com.bit.springboard.dto.NoticeDto;
import com.bit.springboard.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public void post(NoticeDto noticeDto) {
        noticeDao.post(noticeDto);
    }

    @Override
    public void modify(NoticeDto noticeDto) {
        noticeDao.modify(noticeDto);
    }

    @Override
    public void delete(int id) {
        noticeDao.delete(id);
    }

    @Override
    public List<NoticeDto> getNoticeList() {
        return noticeDao.getNoticeList();
    }

    @Override
    public NoticeDto getNotice(int id) {
        return noticeDao.getNotice(id);
    }
}
