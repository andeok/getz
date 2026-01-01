package kr.getz.personal.auth.controller;

import kr.getz.personal.auth.dto.request.LoginRequest;
import kr.getz.personal.auth.dto.response.LoginResponse;
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

	@PostMapping("/signup")
	public ResponseEntity<Long> signUp(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authService.signup(request));
	}


	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok().body(authService.login(request));
	}

}
