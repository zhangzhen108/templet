package com.auth.param.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleAuthorityParamDTO implements Serializable {


    private static final long serialVersionUID = -3497187193539048773L;

    private List<Long> roleIdList;
    private Integer status;

}