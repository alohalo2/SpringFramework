package com.bit.springboard.coupling;

import org.springframework.stereotype.Component;

@Component
public class AppleCarAudio implements CarAudio{

    public AppleCarAudio() {
        System.out.println("AppleCarAudio 객체 생성.");
    }

    public void volumeUp() {
        System.out.println("AppleCarAudio 소리 증가.");
    }

    public void volumeDown() {
        System.out.println("AppleCarAudio 소리 감소.");
    }
}
