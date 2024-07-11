package com.bit.springboard.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class Around {
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("[로그] 로직 처리 전 실행");
        // proceedingJoinPoint.proceed() 현재 실행 중인 메소드
        Object returnObj = proceedingJoinPoint.proceed();
        System.out.println("[로그] 로직 처리 후 실행");
        return returnObj;

    }
}
