package com.auth.common;

/**
 * @author pxu@gaojihealth.com on 2018/5/2
 */
public enum ErrorCodeEnum {

    SUCCESS("200", "操作成功"),
    FAIL("0001", "操作失败"),
    CHECKFAIL("0002", "校验失败"),
    SYSTEM_ERROR("1000", "系统错误"),
    NO_USER_ERROR("1001","用户名密码不存在"),
    NO_USER_ROLE_ERROR("1002","用户角色不存在"),
    NO_USER_AUTHORITY_ERROR("1003","用户权限不存在"),
    //为了分号，
    FEN_HAO_SIMIDA("666666", "分号思密达");

    private String code;
    private String msg;

    private ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
