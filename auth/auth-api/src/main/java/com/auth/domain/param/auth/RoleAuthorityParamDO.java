package com.auth.domain.param.auth;

import lombok.Data;

import java.util.List;

@Data
public class RoleAuthorityParamDO{

    private List<Long> roleIdList;
    private Integer status;
}