package com.mever.api.domain.notice.entity;

import com.mever.api.domain.notice.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
@DynamicInsert
@DynamicUpdate
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
   @Column
   private String title;
    @Column
    private String content;
    @Column
    private String userId;
    @Column
    private String userNm;
    @Column
    private String auth;
    @Column(name = "crt_dt")
    private LocalDateTime crtDate;

    private NoticeDto convertToDto(Notice notice) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setUserId(notice.getTitle());
        noticeDto.setUserId(notice.getContent());
        noticeDto.setUserId(notice.getUserId());
        noticeDto.setUserNm(notice.getUserNm());
        noticeDto.setAuth(notice.getAuth());
        noticeDto.setCrtDt(notice.getCrtDate());
        return noticeDto;
    }
    @PreUpdate
    protected void onUpdate() {
        this.crtDate = LocalDateTime.now();
    }
}