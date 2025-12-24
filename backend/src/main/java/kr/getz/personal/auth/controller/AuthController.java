package kr.getz.personal.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.getz.personal.auth.service.AuthService;
import kr.getz.personal.global.jwt.TokenProvider;
import kr.getz.personal.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	private final TokenProvider tokenProvider;

	@PostMapping("/signup")
	public ResponseEntity<Long> signUp(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authService.signup(request));
	}

	@GetMapping
	public ResponseEntity<?> login() {
		return ResponseEntity.ok(tokenProvider.createAccessToken(1L, "USER"));
	}

	@GetMapping("/{token}")
	public ResponseEntity<?> test(@PathVariable String token) {
		return ResponseEntity.ok(tokenProvider.validateAccessToken(token));
	}

}
