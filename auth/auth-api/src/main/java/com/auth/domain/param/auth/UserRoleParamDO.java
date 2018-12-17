package com.auth.domain.param.auth;

import lombok.Data;

@Data
public class UserRoleParamDO {

    private Integer userId;
    private Integer status;
    private Integer roleId;
}