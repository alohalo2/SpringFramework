package com.bit.springboard.dto;

// DTO(Data Transfer Object): 데이터를 전송하는 객체
//                            화면에서 넘어오는 데이터를 받아서 DB까지 전달하거나
//                            컨트롤러에서 화면으로 데이터를 전송할 때 사용하는 객체
//                            VO(Value Object)랑 쓰임새가 비슷하다.
public class MemberDto {
    private int id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", tell='" + tel + '\'' +
                '}';
    }
}
