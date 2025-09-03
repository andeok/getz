package kr.getz.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(

	@NotNull
	String email,
	String password
) {

}
