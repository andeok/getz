package kr.getz.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.getz.user.domain.User;
import kr.getz.user.domain.UserType;

public record RegisterRequest(

	@NotNull
	String email,
	String password,
	String nickname
) {
	public User toUserEntity() {
		return new User(email, password, nickname);
	}

	public User toRegisterEntity() {
		return new User(email, password, nickname, UserType.USER);
	}

}
