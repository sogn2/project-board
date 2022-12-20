package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "content"),//name 생략
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy"),
})
public class Ex01_ArticleComment_엔트리추가 {
    @Id     //pk키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement 1씩 자동증가
    private Long id;

   @Setter
   @ManyToOne(optional = false)

   private Article article;
    @Setter
    @Column(nullable = false,length = 500)
    String content;


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

}
