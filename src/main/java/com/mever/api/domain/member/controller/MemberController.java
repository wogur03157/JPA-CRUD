package com.mever.api.domain.member.controller;

import com.mever.api.domain.member.dto.MemberDto;
import com.mever.api.domain.member.repository.MemberRepository;
import com.mever.api.domain.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/signup")
    @ApiOperation(value = "회원정보 저장", notes = "회원정보 저장.")
    public ResponseEntity signUp(@RequestBody MemberDto memberDto)throws Exception {
        return memberService.signUp(memberDto);

    }
    @PostMapping("/chklogin")
    @ApiOperation(value = "payment 정보", notes = "payment 정보를 반환합니다.")
    public Object chkLogin(
            @ApiParam(value = "요청 객체", required = true) @RequestBody MemberDto memberDto) throws Exception {
        try {
            return memberService.chkLogin(memberDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    @PostMapping("/{id}/update")
    public ResponseEntity updateMember(@PathVariable("id") Long id, @RequestBody MemberDto memberDto)throws Exception {
       return memberService.updateMember(id, memberDto);
    }
    @PostMapping("/{id}/delete")
    public ResponseEntity deleteMember(@PathVariable("id") Long id) {
        return memberService.deleteMember(id);
    }
    @PostMapping("/list")
    @ApiOperation(value = "회원정보", notes = "회원정보")
    public Object memberList(
            @ApiParam(value = "요청 객체", required = false) @RequestParam(required = false) String email) throws Exception {
        try {
            return memberService.memberList(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
