package com.auth.service;

import com.auth.param.auth.UserRoleParamDTO;
import com.auth.result.auth.UserRoleDTO;

import java.util.List;

public interface UserRoleService {
    /**
     * 查询权限数据
     * @param userRoleParamDTO
     * @return
     */
    public List<UserRoleDTO> queryUserRoleList(UserRoleParamDTO userRoleParamDTO);
}
