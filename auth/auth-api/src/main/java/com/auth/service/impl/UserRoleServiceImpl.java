package com.auth.service.impl;

import com.auth.dao.auth.UserRoleMapper;
import com.auth.domain.param.auth.UserRoleParamDO;
import com.auth.domain.param.trans.auth.UserRoleParamTrans;
import com.auth.domain.result.auth.UserRoleDO;
import com.auth.domain.result.trans.UserRoleTrans;
import com.auth.param.auth.UserRoleParamDTO;
import com.auth.result.auth.UserRoleDTO;
import com.auth.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    UserRoleParamTrans userRoleParamTrans;
    @Resource
    UserRoleTrans userRoleTrans;
    @Override
    public List<UserRoleDTO> queryUserRoleList(UserRoleParamDTO userRoleParamDTO) {
        UserRoleParamDO userRoleParamDO=userRoleParamTrans.toEntity(userRoleParamDTO);
        List<UserRoleDO> userRoleDOList=userRoleMapper.queryUserRoleList(userRoleParamDO);
        List<UserRoleDTO> userRoleDTOList=userRoleTrans.toDto(userRoleDOList);
        return userRoleDTOList;
    }
}
