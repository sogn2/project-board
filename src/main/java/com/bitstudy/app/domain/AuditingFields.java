package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) /*auditing 과 관련된 어노테이션 createat이 없어서 에러 생김 에러 회피*/
@MappedSuperclass
public class AuditingFields {

    //메타데이터
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createAt; // 생성일자
    /* 다른 생성일시 같은 것들은 알아낼 수 있는데, 최초 생성자는 (현재 코드 상태) 인증 받고 오지 않았기 때문에 따로 알아낼 수가 없다.
     * 이 떄 만들어 놓은 jpaConfig 파일을 사용한다. */

    @CreatedBy
    @Column(nullable = false,length = 100)
    private String createBy; // 생성자

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일자

    @LastModifiedBy
    @Column(nullable = false,length = 100)
    private String modifiedBy; // 수정자

}
