package com.auth.common;


/**
 * @author pxu@gaojihealth.com on 2018/5/2
 */
public class BusinessErrorException extends RuntimeException {

    private final String errorCode;

    private final String errorMessage;

    public BusinessErrorException(ErrorCodeEnum errorCodeEnum) {
        this.errorCode=errorCodeEnum.getCode();
        this.errorMessage=errorCodeEnum.getMsg();
    }



}

