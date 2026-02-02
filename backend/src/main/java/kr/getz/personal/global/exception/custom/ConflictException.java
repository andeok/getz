package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ConflictException extends HttpStatusException{
    public ConflictException(ErrorCode errorCode) {
        super(HttpStatus.CONFLICT, errorCode);
    }

}
