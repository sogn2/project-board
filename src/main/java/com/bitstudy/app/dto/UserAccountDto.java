package com.bitstudy.app.dto;

import com.bitstudy.app.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(Long id,
                             String userId,
                             String userPassword,
                             String email,
                             String nickname,
                             String memo,
                             LocalDateTime createAt,
                             String createBy,
                             LocalDateTime modifyedAt,
                             String modifyedBy) {
    public static UserAccountDto of (    Long id,
                                         String userId,
                                         String userPassword,
                                         String email,
                                         String nickname,
                                         String memo,
                                         LocalDateTime createAt,
                                         String createBy,
                                         LocalDateTime modifyedAt,
                                         String modifyedBy){
        return new UserAccountDto(
                id,
                userId,
                userPassword,
               email,
                nickname,
                memo,
               createAt,
                createBy,
                modifyedAt,
                modifyedBy);
    }
/////////////////////////////////////////////////////
public static UserAccountDto from(UserAccount entity){
    return new UserAccountDto(
            entity.getId(),
            entity.getUserId(),
            entity.getUserPassword(),
            entity.getEmail(),
            entity.getNickname(),
            entity.getMemo(),
            entity.getCreateAt(),
            entity.getCreateBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
    );
}
    public UserAccount toEntity(){
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }

}
