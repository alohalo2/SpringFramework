package com.bit.springboard.dto;

import java.time.LocalDateTime;

public class NoticeDto {

    private int id;
    private String title;
    private String content;
    private int WRIETER_ID;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
    private int cnt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWRIETER_ID() {
        return WRIETER_ID;
    }

    public void setWRIETER_ID(int WRIETER_ID) {
        this.WRIETER_ID = WRIETER_ID;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    public LocalDateTime getModdate() {
        return moddate;
    }

    public void setModdate(LocalDateTime moddate) {
        this.moddate = moddate;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "NoticeDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", WRIETER_ID=" + WRIETER_ID +
                ", regdate=" + regdate +
                ", moddate=" + moddate +
                ", cnt=" + cnt +
                '}';
    }
}
