package com.bitstudy.app.repository;

import com.bitstudy.app.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ex05_ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
