package com.bitstudy.app.controller;
/*
*   슬라이스 테스트 : 기능(레이어)별로 잘라서 특정 부문(기능)만 테스트 하는 것
* 
*   - 통합테스트 에너테이션
*       @SpringBootTest - 스프링이 관리하는 모든 빈을 등록시켜서 테스트하기 때문에 무겁다
*                       * 테스트할 가볍게 하기 위해서 @WebMvcTest를 사용해서 web 레이어 관련 빈들만 등록한 상태로 테스트를 할 수도 있다.
*                          단, web 레이어 관련된 빈들만 등록되므로 Service는 등록되지 않는다. 그래서 Mock 관련 언노테이션을 이용해서 가짜로 만들어줘야 한다.
*    - 슬라이스테스트 에너테이션
* 1) @WebMvcTest - 슬라이스 테스트에서 대표적인 어노테이션
* */

public class DataRestTest {
}
