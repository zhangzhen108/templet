package com.auth.param.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoParamDTO implements Serializable {

    private static final long serialVersionUID = 7980548598070568340L;

    private String username;

    private Integer status;
    
}
