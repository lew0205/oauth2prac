package holu.oauth.global.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String msg;
    private final int code;

    public ErrorResponse(ErrorCode errorCode) {
        this.msg = errorCode.getMsg();
        this.code = errorCode.getCode();
    }
}
