package com.ebank.commons.api.Imp;

import com.ebank.commons.api.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * success
     */
    SUCCESS(200, "success"),
    /**
     * exception
     */
    FAILURE(400, "error"),
    /**
     * exception
     */
    Unauthorized(401, "UserName or password is wrong"),
    /**
     * No service
     */
    NOT_FOUND(404, " No service"),
    /**
     * Server Error
     */
    ERROR(500, "Server Error"),
    USER_INPUT_ERROR(400,"Input parameters format error or resources were not exist!"),
    /**
     * Too Many Requests
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests");
    /**
     * Status code
     */
    public final  int code;
    /**
     * message
     */
    final String msg;
}
