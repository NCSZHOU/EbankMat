package com.ebank.commons;
import com.ebank.commons.api.Imp.ResultCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
public class BaseException extends RuntimeException {
    private int code;
    private String message;
    private static final long serialVersionUID = 5782968730281544562L;
    private int status = INTERNAL_SERVER_ERROR.value();

    private BaseException() {
    }
    public BaseException(String message) {
        super(message);
    }

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
