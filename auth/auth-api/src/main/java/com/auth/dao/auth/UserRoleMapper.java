package com.auth.dao.auth;

import com.auth.domain.param.auth.UserRoleParamDO;
import com.auth.domain.result.auth.UserRoleDO;

import java.util.List;

public interface UserRoleMapper {

    List<UserRoleDO> queryUserRoleList(UserRoleParamDO userRoleParamDO);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleDO record);

    int insertSelective(UserRoleDO record);

    UserRoleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleDO record);

    int updateByPrimaryKey(UserRoleDO record);
}