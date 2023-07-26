package com.mever.api.domain.consult.service;


import com.mever.api.domain.consult.entity.Consult;
import com.mever.api.domain.consult.repository.ConsultRepository;
import com.mever.api.domain.consult.dto.ConsultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultService {
    @Autowired
    private ConsultRepository consultRepository;

    // 모든 공지사항 조회
    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }

    // 특정 공지사항 조회
    public Optional<Consult> getConsultById(Long id) {
        return consultRepository.findById(id);
    }

    // 새로운 공지사항 작성
    public Consult createConsult(ConsultDto consultDto) {
        return consultRepository.save(consultDto.toConsultDtoEntity(consultDto));
    }

    // 기존 공지사항 수정
    public Consult updateConsult(ConsultDto consultDto) {
        return consultRepository.save(consultDto.toConsultDtoEntity(consultDto));
    }

    // 특정 공지사항 삭제
    public void deleteConsult(Long id) {
        consultRepository.deleteById(id);
    }

    // 권한 검사를 위한 메서드
    public boolean chkPassword(Long id, String password) {
        Optional<Consult> consult = consultRepository.findById(id);
        return consult.isPresent() && consult.get().getPassword().equals(password);
    }
}
