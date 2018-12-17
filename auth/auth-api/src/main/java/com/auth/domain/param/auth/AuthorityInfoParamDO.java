package com.auth.domain.param.auth;

import lombok.Data;

import java.util.List;

@Data
public class AuthorityInfoParamDO{

    private List<Long> authorityInfoList;

    private Integer status;
}