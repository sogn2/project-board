package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/*롬복을 이용해서 클래스를 엔티티로 변경
* get/setter,toString등의 롬복 어노테이션 사용
* 동등성, 동일성 비교할 수 잇는 코드 넣어볼거임*/
@Getter

@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy"),
})
public class Ex02_1_Article_바인딩설정 {
    @Id     //pk키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement 1씩 자동증가
    private Long id;
    @Setter @Column(nullable=false) String title;
    @Setter @Column(nullable=false,length = 10000) private String content;
    @Setter private String hashtag;
    /**
    *       양방향 바인딩
    * */
    @OrderBy("id")
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    //메타데이터
   @CreatedDate
   @Column(nullable=false)
   private LocalDateTime createAt;//생성일시

   @CreatedBy
   @Column(nullable=false,length = 100)
   private String createBy;//생성자

    @LastModifiedDate
    @Column(nullable=false)
    private LocalDateTime modifiedAt;//수정일시

    @LastModifiedBy
    @Column(nullable=false,length = 100)
    private String modifiedBy;//수정자

    /*Entity를 만들때는 무조건 기본 생성자가 필요 */

    protected Ex02_1_Article_바인딩설정(){}

    /*
    *   사용자가 입력하는 값만 받기. 나머지는 시스템이 알엇 하게 해주면 됨.
    * */

    private Ex02_1_Article_바인딩설정(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

/*정적 팩토리 메소드*/
    public static Ex02_1_Article_바인딩설정 of(String title, String content, String hashtag){
        return new Ex02_1_Article_바인딩설정(title, content, hashtag);

    }

    /*엄청 어려운 개념
    *
    * collection: 객체의 모음(그룹)
    *               자바가 제공하는 최상위 컬레션(인터페이스)
    *               하이버네이트는 중복을 허용하고 순서를 보장하지 않는다고 가정
    *
    *   Set : 중복 허용 안함. 순서도 보장하지 않음
    *   List : 중복 허용, 순서 있음
    *   Map : key와 value 구조로 되어 있는 특수 컬렉션
    *
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex02_1_Article_바인딩설정 article = (Ex02_1_Article_바인딩설정) o;
//        return id.equals(article.id);
        return id!= null&& id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
