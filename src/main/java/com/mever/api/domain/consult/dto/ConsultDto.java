package com.mever.api.domain.consult.dto;


import com.mever.api.domain.consult.entity.Consult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultDto {
    private Long id;
    private String userId;
    private String title;
    private String content;
    private String userNm;
    private String password;
    private LocalDateTime crtDt;

    public static Consult toConsultDtoEntity(ConsultDto noticeDto) {
        Consult consult = new Consult();
        consult.setId(noticeDto.getId());
        consult.setUserId(noticeDto.getUserId());
        consult.setTitle(noticeDto.getTitle());
        consult.setContent(noticeDto.getContent());
        consult.setUserNm(noticeDto.getUserNm());
        consult.setPassword(noticeDto.getPassword());
        consult.setCrtDate(noticeDto.getCrtDt());

        return consult;
    }
}