package com.mever.api.domain.member.service;

import com.mever.api.domain.member.dto.MemberDto;
import com.mever.api.domain.member.entity.Member;
import com.mever.api.domain.member.exception.MemberException;
import com.mever.api.domain.member.exception.MemberExceptionType;
import com.mever.api.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public ResponseEntity signUp(MemberDto memberDto) throws Exception {
        String id= memberDto.getUserId();
        // 아이디 중복 가입 체크
        if (memberRepository.existsByUserId(memberDto.getUserId())) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_USERNAME);
        }
        // 비밀번호 유효성 체크
//        if (!isValidPassword(memberDto.getPassword())) {
//            throw new Exception("비밀번호는 최소 8자 이상이어야 하며, 특수문자와 숫자를 포함해야 합니다.");
//        }
        // 비밀번호 암호화 등 회원 가입 처리 로직
        memberDto.setUseFlag("Y");
        // userRepository를 사용하여 사용자 정보를 저장하는 코드
        Member member=MemberDto.toMemberEntity(memberDto);
        memberRepository.save(member);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity updateMember(Long id,MemberDto memberDto) {
        Member member= memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        if (member != null) {
            if (memberDto.getPassword() != null) {
                member.setPassword(memberDto.getPassword());
            }
            if (memberDto.getUserNm() != null) {
                member.setUserNm(memberDto.getUserNm());
            }
            if (memberDto.getUserRank() != null) {
                member.setUserRank(memberDto.getUserRank());
            }
            if (memberDto.getAuth() != null) {
                member.setAuth(memberDto.getAuth());
            }
            memberRepository.save(member);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
    @Transactional
    public ResponseEntity deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        String flag= member.getUseFlag();
        if(flag!=null&&flag.equals("Y")){
            member.setUseFlag("N");
        }
        if(flag!=null&&flag.equals("N")){
            member.setUseFlag("Y");
        }
        // 필요한 로직 추가
        memberRepository.save(member);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private boolean isValidPassword(String password) {
        // 비밀번호 유효성 체크 로직을 구현하세요.
        // 예를 들어, 비밀번호는 최소 8자 이상이어야 하며, 특수문자와 숫자를 포함해야 한다고 가정합니다.
        // 필요에 따라 정규식을 사용하여 비밀번호 유효성을 검사할 수도 있습니다.
        // 유효한 비밀번호이면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
        if (password.length() < 8) {
            return false;
        }
        // 특수문자 포함 여부 체크
        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }
        // 숫자 포함 여부 체크
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    @Transactional
    public ResponseEntity chkLogin(MemberDto memberDto) {
        String id= memberDto.getUserId();
        String password= memberDto.getPassword();
        boolean member = memberRepository.existsByUserIdAndPassword(id,password);
        if(member) {
            Member user=memberRepository.findByUserIdAndPassword(id,password).orElse(null);
            if(user.getUseFlag().equals("N")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public Object memberList(String email) {
        if(email==null||email.equals("")) {
            return memberRepository.findAll();
        }
        return memberRepository.findByUserId(email).orElse(null);
    }
}
