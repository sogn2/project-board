package com.bitstudy.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@WebMvcTest
public class Ex07_3_DataRestTest_실패하는테스트 {
    /*  MockMvc 테스트 방법
        1) MockMvc 생성 ( 빈 준비)
        2) MockMvc 에게 요청에 대한 정보를 입력
        3) 요청에 대한 응답값을 expect를 이용해서 테스트를 한다.
        4) expect 다 통과 하면 테스트 통과
    *
    * */
    private  final MockMvc mvc;

    public Ex07_3_DataRestTest_실패하는테스트(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    @DisplayName("[api] 게시글 리스트 전체 조회")
    @Test
    void articles() throws  Exception{
//        컨트롤 스페이스바 ;' 알트앤터
        MockMvcRequestBuilders.get("/api/articles/");
        mvc.perform(get("/api/articles")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


    }
}
