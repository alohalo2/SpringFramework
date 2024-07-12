package com.bit.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

    //    @RequestMapping(value = "/member/join.do", method = RequestMethod.GET) == @GetMapping("/member/join.do")
    @RequestMapping(value = "/join.do", method = RequestMethod.GET)
    public String joinView() {
        // return 값은 화면을 찾는 것이기 때문에 생략 x
        return "member/join";
    }

    @GetMapping("/login.do")
    public String loginView(){
        return"member/login";

    }
}
