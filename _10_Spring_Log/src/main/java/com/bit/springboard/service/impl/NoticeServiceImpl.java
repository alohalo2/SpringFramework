package com.bit.springboard.service.impl;

import com.bit.springboard.common.LogConsole;
import com.bit.springboard.dao.NoticeDao;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements BoardService {
    private NoticeDao noticeDao;
    private LogConsole logConsole;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
        this.logConsole = new LogConsole();
    }

    @Override
    public void post(BoardDto boardDto) {
        logConsole.consoleLog();
        noticeDao.post(boardDto);
    }

    @Override
    public void modify(BoardDto boardDto) {
        logConsole.consoleLog();
        noticeDao.modify(boardDto);
    }

    @Override
    public void delete(int id) {
        logConsole.consoleLog();
        noticeDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList() {
        logConsole.consoleLog();
        return noticeDao.getNoticeList();
    }

    @Override
    public BoardDto getBoard(int id) {
        logConsole.consoleLog();
        return noticeDao.getNotice(id);
    }
}
