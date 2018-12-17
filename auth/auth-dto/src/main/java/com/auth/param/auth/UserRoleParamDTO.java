package com.auth.param.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleParamDTO implements Serializable {


    private static final long serialVersionUID = -807318372057904520L;
    private Integer userId;

    private Integer status;
}