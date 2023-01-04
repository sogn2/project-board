package com.bitstudy.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 - 게시글")
class ArticleControllerTest {

    private  final MockMvc mvc;


    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
}