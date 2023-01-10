package com.bitstudy.app.controller;

import com.bitstudy.app.config.SecurityConfig;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** 지금 상태로 테스트 돌리면 콘솔창에 401이 뜬다. (401: 파일을 찾긴 찾았는데 인증을 못받아서 못들어간다 라는 뜻.)
 이유는 기본 웹 시큐리티를 불러와서 그런거다.
 config > SecurityConfig 를 읽어오게 하면 된다.
 방법: @Import(SecurityConfig.class) 를 클래스 레벨에 넣어서 현재 이 test 코드에서도 읽히게 만들어줌
 */

@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 - 게시글")
class ArticleControllerTest {

    private final MockMvc mvc;
    @MockBean private ArticleService articleService;
    /* @MockBean: 테스트시 테스트에 필요한 객체를 bean 으로 등록시켜서 기존 객체 대신 사용 할 수 있게 만들어줌

    ArticleController 에 있는 private final ArticleService articleService; 부분의 articleService를 배제하기 위해서 @MockBean 사용함. 이유는 MockMvc 가 입출력 관련된 것들만 보게 하기 위해서 진퉁 서비스 로직을 끊어주기 위해서 @MockBean 사용

    *  */



    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    /**1) 게시판 (리스트) 페이지*/
    @Test
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void articlesAll() throws Exception {
        /*  eq 는 equal 이라는 뜻  */
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));

        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }

    /**2) 게시글 (상세) 페이지*/
    @Test
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    public void articlesOne() throws Exception {

        Long articleId = 1L;
        given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());

        mvc.perform(get("/articles/1")) /** 테스트니까 그냥 1번 글 가져와라 할거임 */
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

        then(articleService).should().getArticle(articleId);
    }

//    /**3) 게시판 검색 전용*/
//    @Disabled("구현중")
//    @Test
//    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상호출")
//    public void articlesSearch() throws Exception {
//        mvc.perform(get("/articles/search"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("articles/search"));
//    }
//
//    /**4) 해시태그 검색 전용 페이지*/
//    @Disabled("구현중")
//    @Test
//    @DisplayName("[view][GET] 게시글 해시태그 전용 페이지 - 정상호출")
//    public void articlesSearchHashtag() throws Exception {
//        mvc.perform(get("/articles/search-hashtag"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("articles/search-hashtag"));
//    }

    ///////////////////////////////////////
    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "bitstudy",
                "password",
                "bitstudy@email.com",
                "bitstudy",
                "memo memmo",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }

}

















