package com.auth.param.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleInfoParamDTO implements Serializable {
    private static final long serialVersionUID = 5589159180530229015L;

    private List<Long>  roleIdList;
    private Integer status;
}