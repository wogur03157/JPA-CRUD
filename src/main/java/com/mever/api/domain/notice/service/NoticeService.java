package com.mever.api.domain.notice.service;


import com.mever.api.domain.notice.dto.NoticeDto;
import com.mever.api.domain.notice.entity.Notice;
import com.mever.api.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    // 모든 공지사항 조회
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // 특정 공지사항 조회
    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }

    // 새로운 공지사항 작성
    public Notice createNotice(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toNoticeEntity(noticeDto));
    }

    // 기존 공지사항 수정
    public Notice updateNotice(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toNoticeEntity(noticeDto));
    }

    // 특정 공지사항 삭제
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    // 권한 검사를 위한 메서드
    public boolean hasAdminAuth(String auth) {
        return "admin".equals(auth);
    }
}
