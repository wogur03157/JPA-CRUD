package com.mever.api.domain.notice.dto;


import com.mever.api.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private Long id;
    private String userId;
    private String title;
    private String content;
    private String userNm;
    private String auth;
    private LocalDateTime crtDt;

    public static Notice toNoticeEntity(NoticeDto noticeDto) {
        Notice notice = new Notice();
        notice.setId(noticeDto.getId());
        notice.setUserId(noticeDto.getUserId());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setUserNm(noticeDto.getUserNm());
        notice.setAuth(noticeDto.getAuth());
        notice.setCrtDate(noticeDto.getCrtDt());

        return notice;
    }
}