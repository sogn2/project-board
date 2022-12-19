package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;
/*롬복을 이용해서 클래스를 엔티티로 변경
* get/setter,toString등의 롬복 어노테이션 사용
* 동등성, 동일성 비교할 수 잇는 코드 넣어볼거임*/
@Getter
@Setter
@ToString
@Entity
public class Article {
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    //메타데이터
    private LocalDateTime createAt;//생성일시
    private String createBy;//생성자
    private LocalDateTime modifiedAt;//수정일시
    private String modifiedBy;//수정자


}
