package kr.getz.personal.auth.dto.request;

public record LoginRequest(
    String email,
    String password
) {

}
