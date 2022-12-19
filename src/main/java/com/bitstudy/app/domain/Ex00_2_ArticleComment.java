package com.bitstudy.app.domain;

import java.time.LocalDateTime;

public class Ex00_2_ArticleComment {
    private Long id;

    private Article article;
    private String content;


    //메타데이터
    private LocalDateTime createAt;//생성일시
    private String createBy;//생성자
    private LocalDateTime modifiedAt;//수정일시
    private String modifiedBy;//수정자

}
