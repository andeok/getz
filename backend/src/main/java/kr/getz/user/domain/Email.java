package kr.getz.user.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

	private static final Pattern EMAIL_PATTERN =
		Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

	@Column(name = "email")
	private String value;

	public Email(String value) {
		validateEmailPattern(value);
		this.value = value;
	}

	public void validateEmailPattern(String email) {
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new AuctionException(ExceptionCode.EMAIL_INVALID_FORMAT);
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
		Email email = (Email) o;
		return Objects.equals(value, email.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

}

