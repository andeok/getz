package kr.getz.personal.auth.service;

import kr.getz.personal.auth.dto.request.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.personal.member.domain.Member;
import kr.getz.personal.member.dto.SignUpRequest;
import kr.getz.personal.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(SignUpRequest request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입 된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = request.toMember(encodedPassword);

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest request) {

        Member member = memberRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        boolean matches = passwordEncoder.matches(request.password(), member.getPassword());

        if (!matches) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // TODO: jwt 토큰 발급 처리 예정

    }
}
