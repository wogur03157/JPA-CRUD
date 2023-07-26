package com.mever.api.domain.consult.entity;

import com.mever.api.domain.consult.dto.ConsultDto;
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
@Table(name = "consult")
@DynamicInsert
@DynamicUpdate
public class Consult {
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
    private String password;
    @Column(name = "crt_dt")
    private LocalDateTime crtDate;

    private ConsultDto convertToDto(Consult consult) {
        ConsultDto consultDto = new ConsultDto();
        consultDto.setUserId(consult.getTitle());
        consultDto.setUserId(consult.getContent());
        consultDto.setUserId(consult.getUserId());
        consultDto.setUserNm(consult.getUserNm());
        consultDto.setPassword(consult.getPassword());
        consultDto.setCrtDt(consult.getCrtDate());
        return consultDto;
    }
    @PreUpdate
    protected void onUpdate() {
        this.crtDate = LocalDateTime.now();
    }
}