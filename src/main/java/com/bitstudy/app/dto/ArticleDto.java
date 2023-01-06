package com.bitstudy.app.dto;


import com.bitstudy.app.domain.Article;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createAt,
        String createBy,
        LocalDateTime modifyedAt,
        String modifyedBy) {

    public static  ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createAt, String createBy, LocalDateTime modifyedAt, String modifyedBy){
        return new ArticleDto( id,  userAccountDto,  title,  content,  hashtag,  createAt,  createBy,  modifyedAt,  modifyedBy);
    }
    public static ArticleDto from(Article entity){
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreateAt(),
                entity.getCreateBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
    public Article toEntity(){
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}
