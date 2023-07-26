package com.mever.api.domain.upload.service;

import com.mever.api.domain.upload.dto.UploadDto;
import com.mever.api.domain.upload.entity.Upload;
import com.mever.api.domain.upload.exception.UploadException;
import com.mever.api.domain.upload.exception.UploadExceptionType;
import com.mever.api.domain.upload.repository.UploadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {
    private final UploadRepository uploadRepository;
    @Transactional
    public ResponseEntity saveVideo(UploadDto uploadDto) {
        // userRepository를 사용하여 사용자 정보를 저장하는 코드
        Upload upload = UploadDto.toEntity(uploadDto);
        uploadRepository.save(upload);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity updateVideo(Long id, UploadDto uploadDto) {
        Upload upload = uploadRepository.findById(id).orElseThrow(() -> new UploadException(UploadExceptionType.NOT_FOUND_VIDEO));
        if (upload != null) {
            if (uploadDto.getTitle() != null) {
                upload.setTitle(uploadDto.getTitle());
            }
            if (uploadDto.getUrl() != null) {
                upload.setUrl(uploadDto.getUrl());
            }
            if (uploadDto.getContent() != null) {
                upload.setContent(uploadDto.getContent());
            }
            if (uploadDto.getCrtId() != null) {
                upload.setCrtId(uploadDto.getCrtId());
            }
            if (uploadDto.getUpdId() != null) {
                upload.setUpdId(uploadDto.getUpdId());
            }
            if (uploadDto.getUrlType() != null) {
                upload.setUrlType(uploadDto.getUrlType());
            }
            if (uploadDto.getItem() != null) {
                upload.setItem(uploadDto.getItem());
            }
            uploadRepository.save(upload);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
    @Transactional
    public ResponseEntity deleteVideo(Long id) {
        uploadRepository.delete(uploadRepository.findById(id)
                .orElseThrow(() -> new UploadException(UploadExceptionType.NOT_FOUND_VIDEO)));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @Transactional
    public Object videoList(Long id) {
        if(id==null) {
            return uploadRepository.findAll();
        }
        return uploadRepository.findById(id).orElse(null);
    }
}
