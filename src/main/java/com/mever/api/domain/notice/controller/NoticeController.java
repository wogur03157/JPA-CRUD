package com.mever.api.domain.notice.controller;

import com.mever.api.domain.notice.dto.NoticeDto;
import com.mever.api.domain.notice.entity.Notice;
import com.mever.api.domain.notice.repository.NoticeRepository;
import com.mever.api.domain.notice.service.NoticeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "공지사항")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "공지사항 리스트", notes = "모든리스트.")
    @GetMapping("/list")
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    @ApiOperation(value = "해당 공지정보", notes = "정보")
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
        Optional<Notice> optionalNotice = noticeService.getNoticeById(id);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            return ResponseEntity.ok(notice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "공지쓰기", notes = "공지쓰기")
    @PostMapping("/write")
    public ResponseEntity<?> createNotice(@RequestBody NoticeDto noticeDto) {
        Notice savedNotice = noticeService.createNotice(noticeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotice);
    }

    @ApiOperation(value = "공지 수정", notes = "공지 수정.")
    @PutMapping("/modify")
    public ResponseEntity<?> updateNotice(@RequestBody NoticeDto noticeDto) {
        if (noticeService.hasAdminAuth(noticeDto.getAuth())) {
            Notice updatedNotice = noticeService.updateNotice(noticeDto);
            if (updatedNotice != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedNotice);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ApiOperation(value = "공지 삭제", notes = "공지 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable Long id, @RequestParam String auth) {
        if (noticeService.getNoticeById(id).isPresent()) {
            if (noticeService.hasAdminAuth(auth)) {
                noticeService.deleteNotice(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
