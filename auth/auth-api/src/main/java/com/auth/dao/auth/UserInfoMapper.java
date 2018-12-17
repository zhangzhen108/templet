package com.auth.dao.auth;

import com.auth.domain.param.auth.UserInfoParamDO;
import com.auth.domain.result.auth.UserInfoDO;

public interface UserInfoMapper {

    UserInfoDO queryUser(UserInfoParamDO userInfoParamDO);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}