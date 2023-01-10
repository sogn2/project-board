package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/** QueryDsl 의 QuerydlsPredicateExecutor 와 QuerysalBinberCustomizer 를 이용해서 검색 기능을 만들어볼거다. */

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>
        ,QuerydslBinderCustomizer<QArticle> {
    /* 조심할것: QuerydslBinderCustomizer 는 QArticle을 사용하는데 이건 build.gradle 에서 queryDsl 을 build 하고 와야함 */

    /* 설명: QuerydslPredicateExecutor 는 Article 안에 있는 모든 필드에 대한 기본 검색기능을 추가해준다.
            순서: 1. 바인딩
                 2. 검색용 필드를 추가
    *  */

    /* 제목으로 검색할때 */
    Page<Article> findByTitleContaining(String title, Pageable pageable);

    /* 내용으로 검색할때 */
    Page<Article> findByContentContaining(String title, Pageable pageable);

    /* 유저아이디로 검색할때 */
    Page<Article> findByUserAccount_UserIdContaining(String title, Pageable pageable);

    /* 닉네임으로 검색할때 */
    Page<Article> findByUserAccount_NicknameContaining(String title, Pageable pageable);

    /* 해시태그로 검색할때 */
    Page<Article> findByhashtagContaining(String title, Pageable pageable);


    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        /* 1. 바인딩
            현재 QuerydslPredicateExecutor 때문에 Article 에 있는 모든 필드에 대한 검색이 열려있는 상태이다. 근데 우리가 원하는건 선택적 필드(제목, 본문, id, 글쓴이, 해시태그)만 검색에 사용되도록 하고 싶다.
            그래서 선택적으로 검색을 하게 하기 위해서 bindings.excludeUnlistedProperties 를 쓴다.
            excludeUnlistedProperties 는 리스팅을 하지 않은 프로퍼티는 검색에 포함할지 말지를 결정할 수 있는 메서드이다.
            true 면 검색에서 제외, false 는 모든 프로퍼티를 열어주는거
        *  */
        bindings.excludeUnlistedProperties(true);


        /* 2. 검색용(원하는) 필드를 지정(추가) 하는 부분
            including 을 이용해서 title, content, createdBy, createdAt, hashtag 검색 가능하게 만들거임.
            (id 는 인증기능 달아서 유저 정보를 알아 올 수 있을때 할거임)
            including 사용법: 'root.필드명'
        * */
        bindings.including(root.title, root.content, root.createAt, root.createBy, root.hashtag);

        /* 3. '정확환 검색'만 됐었는데 'or 검색' 가능하게 바꾸기  */
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${문자열}' 로 들어감. % 없이 들어가는거라서 수동으로 넣어줘야 함.

        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${문자열}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createAt).first(DateTimeExpression::eq); // 이건 날짜니까 DateTimeExpression 으로 하고, eq는 equals의 의미. 날짜필드는 정확한 검색만 되도록 설정. 근데 이렇게 하면 시분초가 다 0으로 인식됨. 이부분은 별도로 시간 처리할때 건드릴거임.
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);

    }

    /* ArticleCommentRepository.java 가서 똑같이 하기 */

}
