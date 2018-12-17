package com.auth.dao.auth;

import com.auth.domain.param.auth.RoleInfoParamDO;
import com.auth.domain.result.auth.RoleInfoDO;

import java.util.List;

public interface RoleInfoMapper {

    List<RoleInfoDO> queryRoleInfoList(RoleInfoParamDO roleInfoParamDO);

    int deleteByPrimaryKey(Long id);

    int insert(RoleInfoDO record);

    int insertSelective(RoleInfoDO record);

    RoleInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleInfoDO record);

    int updateByPrimaryKey(RoleInfoDO record);
}