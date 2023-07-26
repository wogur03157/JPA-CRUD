package com.mever.api.domain.upload.dto;

import com.mever.api.domain.upload.entity.Upload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadDto {
    private Long id;
    private String title;
    private String url;
    private String content;
    private LocalDateTime crtDt;
    private Long crtId;
    private LocalDateTime updDt;
    private String updId;
    private String urlType;
    private String item;
    public static Upload toEntity(UploadDto uploadDto) {
        Upload upload = new Upload();
        upload.setTitle(uploadDto.getTitle());
        upload.setUrl(uploadDto.getUrl());
        upload.setContent(uploadDto.getContent());
        upload.setCrtDt(uploadDto.getCrtDt());
        upload.setCrtId(uploadDto.getCrtId());
        upload.setUpdatedDate(uploadDto.getUpdDt());
        upload.setUpdId(uploadDto.getUpdId());
        upload.setUrlType(uploadDto.getUrlType());
        upload.setItem(uploadDto.getItem());
        return upload;
    }

}