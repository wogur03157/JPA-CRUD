package com.mever.api.domain.member.entity;

import com.mever.api.domain.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
   @Column
    private String userId;
   @Column
    private String password;
   @Column
    private String userNm;
   @Column
    private String auth;
   @Column
    private String userRank;
   @Column
    private LocalDateTime crtDt;
   @Column
    private String crtId;
    @Column(name = "upd_dt")
    private LocalDateTime updatedDate;
   @Column
    private String updId;
   @Column
    private String useFlag;

    private MemberDto convertToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        memberDto.setUserId(member.getUserId());
        memberDto.setUserNm(member.getUserNm());
        memberDto.setAuth(member.getAuth());
        memberDto.setUserRank(member.getUserRank());
        memberDto.setCrtDt(member.getCrtDt());
        memberDto.setCrtId(member.getCrtId());
        memberDto.setUpdDt(member.getUpdatedDate());
        memberDto.setUpdId(member.getUpdId());
        memberDto.setUseFlag(member.getUseFlag());
        return memberDto;
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}