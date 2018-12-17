package com.auth.service;

import com.auth.param.auth.RoleAuthorityParamDTO;
import com.auth.result.auth.RoleAuthorityDTO;

import java.util.List;

public interface RoleAuthorityService {

    public List<RoleAuthorityDTO> queryAuthorityInfoList(RoleAuthorityParamDTO roleAuthorityParamDTO);
}
