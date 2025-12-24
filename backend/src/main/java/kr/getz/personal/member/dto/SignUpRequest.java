package kr.getz.personal.member.dto;

import kr.getz.personal.member.domain.Member;

public record SignUpRequest(
	String email,
	String password,
	String name
) {

	public Member toMember(String encodedPassword) {

		return Member.builder()
			.email(email)
			.password(encodedPassword)
			.name(name)
			.build();
	}
}
