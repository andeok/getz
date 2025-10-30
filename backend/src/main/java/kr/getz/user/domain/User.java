package kr.getz.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.getz.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Embedded
	private Email email;

	@Embedded
	private Password password;

	private String nickname;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType = UserType.USER;

	public User(String email, String password, String nickname) {
		this.email = new Email(email);
		this.password = new Password(password);
		this.nickname = nickname;
	}

	public User(String email, String password, String nickname, UserType userType) {
		this.email = new Email(email);
		this.password = new Password(password);
		this.nickname = nickname;
		this.userType = userType;
	}

	public boolean isDifferent(String targetPassword) {
		return password.isDifferent(targetPassword);
	}
}
