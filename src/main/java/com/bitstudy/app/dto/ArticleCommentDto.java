package com.bitstudy.app.dto;


import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.ArticleComment;
import com.bitstudy.app.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createAt,
        String createBy,
        LocalDateTime modifyedAt,
        String modifyedBy) {

    public static ArticleCommentDto of(        Long id,
                                               Long articleId,
                                               UserAccountDto userAccountDto,
                                               String content,
                                               LocalDateTime createAt,
                                               String createBy,
                                               LocalDateTime modifyedAt,
                                               String modifyedBy){
        return new ArticleCommentDto( id,  articleId,userAccountDto,  content,   createAt,  createBy,  modifyedAt,  modifyedBy);
    }
    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreateAt(),
                entity.getCreateBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article article, UserAccount userAccount) {
        return ArticleComment.of(
                article,
                userAccount,
                content
        );
    }
}
