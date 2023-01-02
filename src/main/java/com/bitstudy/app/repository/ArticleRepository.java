package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/*TDD를 위해서 임시로 만들어놓은 저장소 (이걸로 DB 접근)
 * 할일 - 클래스 레벨에 @RepositoryRestResource 넣어서 해당 클래스를 spring rest
 * */
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
