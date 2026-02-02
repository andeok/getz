package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {

    private final ErrorCode errorCode;

    public IllegalArgumentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
