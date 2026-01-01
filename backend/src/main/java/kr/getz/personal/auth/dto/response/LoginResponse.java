package kr.getz.personal.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
    String accessToken,
    String refreshToken
) {

}
