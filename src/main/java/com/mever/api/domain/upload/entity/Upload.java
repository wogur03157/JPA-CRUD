package com.mever.api.domain.upload.entity;

import com.mever.api.domain.upload.dto.UploadDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "upload")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
   @Column
    private String title;
   @Column
    private String url;
   @Column
    private String content;
   @Column
    private LocalDateTime crtDt;
   @Column
    private Long crtId;
   @Column
    private String updId;
    @Column(name = "upd_dt")
    private LocalDateTime updatedDate;
   @Column
    private String urlType;
    @Column
    private String item;

    private UploadDto convertToDto(Upload upload) {
        UploadDto uploadDto = new UploadDto();
        uploadDto.setId(upload.getId());
        uploadDto.setTitle(upload.getTitle());
        uploadDto.setUrl(upload.getUrl());
        uploadDto.setContent(upload.getContent());
        uploadDto.setCrtDt(upload.getCrtDt());
        uploadDto.setCrtId(upload.getCrtId());
        uploadDto.setUpdDt(upload.getUpdatedDate());
        uploadDto.setUpdId(upload.getUpdId());
        uploadDto.setUrlType(upload.getUrlType());
        return uploadDto;
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}