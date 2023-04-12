package holu.oauth.global.exception;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{

    private final ErrorCode errorCode;

    public BasicException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
}
