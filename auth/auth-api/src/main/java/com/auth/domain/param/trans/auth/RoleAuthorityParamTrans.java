package com.auth.domain.param.trans.auth;

import com.auth.common.EntityMapper;
import com.auth.domain.param.auth.RoleAuthorityParamDO;
import com.auth.param.auth.RoleAuthorityParamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleAuthorityParamTrans extends EntityMapper<RoleAuthorityParamDTO, RoleAuthorityParamDO> {
}
