package com.auth.dao.auth;

import com.auth.domain.param.auth.RoleAuthorityParamDO;
import com.auth.domain.result.auth.RoleAuthorityDO;

import java.util.List;

public interface RoleAuthorityMapper {

    List<RoleAuthorityDO> queryRoleAuthorityList(RoleAuthorityParamDO roleAuthorityParamDO);

    int deleteByPrimaryKey(Long id);

    int insert(RoleAuthorityDO record);

    int insertSelective(RoleAuthorityDO record);

    RoleAuthorityDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAuthorityDO record);

    int updateByPrimaryKey(RoleAuthorityDO record);
}