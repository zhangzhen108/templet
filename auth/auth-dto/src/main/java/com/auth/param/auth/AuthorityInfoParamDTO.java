package com.auth.param.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorityInfoParamDTO implements Serializable {


    private static final long serialVersionUID = 3229126784951236987L;
    private List<Long> authorityInfoList;

    private Integer status;
}