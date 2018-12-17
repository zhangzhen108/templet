package com.auth.dao.auth;

import com.auth.domain.param.auth.AuthorityInfoParamDO;
import com.auth.domain.result.auth.AuthorityInfoDO;

import java.util.List;

public interface AuthorityInfoMapper {


    List<AuthorityInfoDO> queryAuthorityList(AuthorityInfoParamDO authorityInfoParamDO);

    int deleteByPrimaryKey(Long id);

    int insert(AuthorityInfoDO record);

    int insertSelective(AuthorityInfoDO record);

    AuthorityInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthorityInfoDO record);

    int updateByPrimaryKey(AuthorityInfoDO record);
}