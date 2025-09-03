package kr.getz.auth.service;

public record AuthUser(
	Long id
) {

	public static AuthUser from(Long id) {
		return new AuthUser(id);
	}
}
