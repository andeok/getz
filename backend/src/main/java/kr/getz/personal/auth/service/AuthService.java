package kr.getz.personal.auth.service;

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

		if(memberRepository.existsByEmail(request.email()))
			throw new IllegalArgumentException("이미 가입 된 이메일입니다.");

		String encodedPassword = passwordEncoder.encode(request.password());

		Member member = request.toMember(encodedPassword);

		return memberRepository.save(member).getId();
	}
}
