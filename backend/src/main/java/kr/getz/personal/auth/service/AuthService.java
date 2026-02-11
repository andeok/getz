package kr.getz.personal.auth.service;

import kr.getz.personal.auth.dto.request.LoginRequest;
import kr.getz.personal.auth.dto.response.LoginResponse;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.BadRequestException;
import kr.getz.personal.global.exception.custom.UnauthorizedException;
import kr.getz.personal.global.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.personal.member.entity.Member;
import kr.getz.personal.member.dto.SignUpRequest;
import kr.getz.personal.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public Long signup(SignUpRequest request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new BadRequestException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = request.toMember(encodedPassword);

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        Member member = memberRepository.findByEmail(request.email())
            .orElseThrow(() -> new UnauthorizedException(ErrorCode.ID_PASSWORD_NOT_MATCH));

        boolean matches = passwordEncoder.matches(request.password(), member.getPassword());

        if (!matches) {
            throw new UnauthorizedException(ErrorCode.ID_PASSWORD_NOT_MATCH);
        }

        String accessToken = tokenProvider.createAccessToken(member.getId(),
            member.getRole().name());

        String refreshToken = tokenProvider.createRefreshToken(member.getId());

        redisTemplate.opsForValue().set(String.valueOf(member.getId()), refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }
}
