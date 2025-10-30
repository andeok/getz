package kr.getz.user.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.getz.auth.service.PasswordEncoder;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

	//비밀번호는 최소 6자 이상이어야 하며, 영어 문자와 숫자를 각각 1개 이상 포함해야 한다.
	private static final Pattern PASSWORD_PATTERN =
		Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~`!@#$%^&*()_\\-+=]{6,}$");

	@Column(name = "password")
	private String value;

	public Password(String value) {
		validatePasswordPattern(value);
		this.value = PasswordEncoder.encodeWithGeneralSalt(value);
	}

	public boolean isDifferent(String password) {
		String targetPassword = PasswordEncoder.encodeWithSpecificSalt(password, value);
		return !value.equals(targetPassword);
	}

	private void validatePasswordPattern(String password) {
		if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
			throw new AuctionException(ExceptionCode.PASSWORD_INVALID_FORMAT);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Password targetPassword = (Password) o;
		return Objects.equals(value, targetPassword.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

}
