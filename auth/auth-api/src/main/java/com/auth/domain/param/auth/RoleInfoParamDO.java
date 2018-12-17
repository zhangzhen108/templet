package com.auth.domain.param.auth;

import lombok.Data;

import java.util.List;

@Data
public class RoleInfoParamDO{

    private List<Long> roleIdList;
    private Integer status;

}