package com.bitstudy.app.repository;

import com.bitstudy.app.config.JpaConfig;
import com.bitstudy.app.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@DataJpaTest //슬라이드 테스트
/**슬라이드 테스트란
* 지난번 TDD 때 각 메서드를 다 남남으로 서로를 알아보지 못하게 만들었다. 이것처럼 메서드를 가가각 테스트한 결과를 서로 못보게 잘라서 만드는 것.
* */
@Import(JpaConfig.class) /*// 원래대로라면 JPA에서 모든 정보를 컨트롤 해야되는데 JpaConfig의 경우는 읽어오지 못한다.
이건 시스템에서 만드는게 아니라 별도로 만든 파일이기 때문이다. 그래서 따로 import 해줘야 한다
안하면 config안에 명시해놨던 JpaAuditing 기능이 동작하지 않는다.

*/
class Ex04_JpaRepositoryTest {
/*db연동*/

 private final    Ex04_ArticleRepository articleRepository;
 private  final  Ex05_ArticleCommentRepository articleCommentRepository;

    public Ex04_JpaRepositoryTest(@Autowired Ex04_ArticleRepository articleRepository,@Autowired Ex05_ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    /*
    *   - 트랜잭션시 사용하는 메서드
    * 사용법 : repository명.메소드()
    * 1) findAll() - 모든 컬럼을 조회할 때 사용. 페이징(pageable) 가능
    *                당연히 select 작업을 하지만, 잠깐 사이에 해당 테이블에 어떤 변화가 있었는지 알 수 없기 때문에 select 전에 먼저 최신 데이터를 잡기 위해서 update를 한다.
    *               동작 순서 : update -> select
    * 2) findById() - 한 건에 대한 데이터 조회시 사용
    *                   primary key로 레코드 한건 조회
    * 3) save() -레코드 저장할때 사용 (insert, update)
    * 4) count() - 레코드 개수 뽑을때
    * 5) delete() - 레코드 삭제
    * */
    @Test
    @DisplayName("셀렉트 테스트")
    void selectTest(){
/*
*   셀릭팅을 할꺼니까 articleRepository를 기준으로 테스트 할거임
*
*   maven방식: dao -> mapper로 정보 보내고 db 갔다 와서 c까지 돌려보낼건데 dao에서 DTO를 list에 담아서 return
* */
       List<Article> articles= articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(100);
    }
    @Test
    void selectOntTest() {
//        List<Article> articles = articleRepository.findById(1);
//        assertThat(articles).isNotNull().hasSize(100);

    }
    @Test
    void insertTest(){
       Long prevCount =  articleRepository.count();
    Article article =   Article.of("제목","내용","#해시태그");
        articleRepository.save(article);
        assertThat( articleRepository.count()).isEqualTo(prevCount +1);
//        Long currCount = articleRepository.count();
       // articleRepository.save();
    }
    @Test
    void updateTest(){

        Article article= articleRepository.findById(1L).orElseThrow();

        String updateHashtag= "#abcd";

        article.setHashtag(updateHashtag);
        Article articleSave =  articleRepository.saveAndFlush(article);

        assertThat(articleSave).hasFieldOrPropertyWithValue("hashtag", updateHashtag);

        //        Long prevCount =  articleRepository.count();
//        Article article =   Article.of("제목","내용","#해시태그");

//        assertThat( articleRepository.count()).isEqualTo(prevCount +1);
//        Long currCount = articleRepository.count();
        // articleRepository.save();
    }
    @Test
    void deleteTest(){

        Article article= articleRepository.findById(1L).orElseThrow();
        long prevArticleCount= articleRepository.count();
        long prevArticleCommentCount = articleCommentRepository.count();
        int deleteCommentSize =article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount-deleteCommentSize);
//        Long prevCount =  articleRepository.count();
//        Article article =   Article.of("제목","내용","#해시태그");

//        assertThat( articleRepository.count()).isEqualTo(prevCount +1);
//        Long currCount = articleRepository.count();
        // articleRepository.save();
    }
}