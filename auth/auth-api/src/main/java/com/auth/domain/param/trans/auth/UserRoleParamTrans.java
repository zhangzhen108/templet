package com.auth.domain.param.trans.auth;

import com.auth.common.EntityMapper;
import com.auth.domain.param.auth.UserRoleParamDO;
import com.auth.param.auth.UserRoleParamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleParamTrans extends EntityMapper<UserRoleParamDTO, UserRoleParamDO> {
}
