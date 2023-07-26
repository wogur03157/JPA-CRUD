package com.mever.api.domain.consult.controller;

import com.mever.api.domain.consult.dto.ConsultDto;
import com.mever.api.domain.consult.entity.Consult;
import com.mever.api.domain.consult.service.ConsultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "상담게시판")
@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @ApiOperation(value = "상담게시판사항 리스트", notes = "모든리스트.")
    @GetMapping("/list")
    public ResponseEntity<List<Consult>> getAllConsults() {
        List<Consult> consult = consultService.getAllConsults();
        return ResponseEntity.ok(consult);
    }

    @ApiOperation(value = "해당 상담게시판정보", notes = "정보")
    @GetMapping("/{id}")
    public ResponseEntity<Consult> getConsultById(@PathVariable Long id) {
        Optional<Consult> optionalConsult = consultService.getConsultById(id);
        if (optionalConsult.isPresent()) {
            Consult notice = optionalConsult.get();
            return ResponseEntity.ok(notice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "상담게시판쓰기", notes = "상담게시판쓰기")
    @PostMapping("/write")
    public ResponseEntity<?> createConsult(@RequestBody ConsultDto consultDto) {
        Consult savedNotice = consultService.createConsult(consultDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotice);
    }

    @ApiOperation(value = "상담게시판 수정", notes = "상담게시판 수정.")
    @PutMapping("/modify")
    public ResponseEntity<?> updateConsult(@RequestBody ConsultDto consultDto) {
        if (consultService.chkPassword(consultDto.getId(), consultDto.getPassword())) {
            Consult updatedConsult = consultService.updateConsult(consultDto);
            if (updatedConsult != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedConsult);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ApiOperation(value = "상담게시판 삭제", notes = "상담게시판 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsult(@PathVariable Long id, @RequestParam(required = false) String password, @RequestParam(required = false) String auth) {
        if (consultService.getConsultById(id).isPresent()) {
            if("admin".equals(auth)){
                consultService.deleteConsult(id);
            }
            if (consultService.chkPassword(id,password)) {
                consultService.deleteConsult(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
