package com.mever.api.domain.upload.controller;

import com.mever.api.domain.upload.dto.UploadDto;
import com.mever.api.domain.upload.repository.UploadRepository;
import com.mever.api.domain.upload.service.UploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "영상")
@RestController
@RequestMapping("/video")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @Autowired
    UploadRepository uploadRepository;

    @PostMapping("/save")
    @ApiOperation(value = "회원정보 저장", notes = "회원정보 저장.")
    public ResponseEntity saveVideo(@RequestBody UploadDto uploadDto)throws Exception {
       return ResponseEntity.ok(uploadService.saveVideo(uploadDto));
    }
    @PostMapping("/{id}/update")
    public ResponseEntity updateVideo(@PathVariable("id") Long id, @RequestBody UploadDto uploadDto)throws Exception {
       return ResponseEntity.ok( uploadService.updateVideo(id, uploadDto));
    }
    @PostMapping("/{id}/delete")
    public ResponseEntity deleteVideo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(uploadService.deleteVideo(id));
    }
    @PostMapping("/list")
    @ApiOperation(value = "영상정보", notes = "영상정보")
    public ResponseEntity videoList(
            @ApiParam(value = "요청 객체", required = false) @RequestParam(required = false) Long id) throws Exception {
        try {
            return ResponseEntity.ok(uploadService.videoList(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
