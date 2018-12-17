package com.auth.service;

import com.auth.param.auth.RoleInfoParamDTO;
import com.auth.result.auth.RoleInfoDTO;

import java.util.List;

public interface RoleInfoService {

    public List<RoleInfoDTO> queryRoleInfoList(RoleInfoParamDTO roleInfoParamDTO);
}
