package com.mever.api.domain.member.dto;


import com.mever.api.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String userId;
    private String password;
    private String userNm;
    private String auth;
    private String userRank;
    private LocalDateTime crtDt;
    private String crtId;
    private LocalDateTime updDt;
    private String updId;
    private String useFlag;

    public static Member toMemberEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setUserId(memberDto.getUserId());
        member.setPassword(memberDto.getPassword());
        member.setUserNm(memberDto.getUserNm());
        member.setAuth(memberDto.getAuth());
        member.setUserRank(memberDto.getUserRank());
        member.setCrtDt(memberDto.getCrtDt());
        member.setCrtId(memberDto.getCrtId());
        member.setUpdatedDate(memberDto.getUpdDt());
        member.setUpdId(memberDto.getUpdId());
        member.setUseFlag(memberDto.getUseFlag());
        return member;
    }
}