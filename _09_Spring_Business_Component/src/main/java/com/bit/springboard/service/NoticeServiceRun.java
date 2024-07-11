package com.bit.springboard.service;

import com.bit.springboard.dto.NoticeDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class NoticeServiceRun {
    public static void main(String[] args) {

        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        NoticeService noticeService = (NoticeService) factory.getBean("noticeServiceImpl");

        // 알림 설정
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setTitle("알림창1");
        noticeDto.setContent("알림창1 입니다.");
        noticeDto.setWRIETER_ID(1);

        noticeService.post(noticeDto);

        // 알림 수정
        NoticeDto modifyNoticeDto = new NoticeDto();

        modifyNoticeDto.setId(3);
        modifyNoticeDto.setTitle("알림창3");
        modifyNoticeDto.setContent("알림창 3입니다.");
        modifyNoticeDto.setModdate(LocalDateTime.now());

        noticeService.modify(modifyNoticeDto);

        // 알림 삭제
        noticeService.delete(2);

        // 알림 목록 조회
        noticeService.getNoticeList().forEach(notice -> {
            System.out.println(notice);
        });

        // 특정 id의 게시글 하나만 조회
        System.out.println(noticeService.getNotice(3));

        factory.close();
    }
}
