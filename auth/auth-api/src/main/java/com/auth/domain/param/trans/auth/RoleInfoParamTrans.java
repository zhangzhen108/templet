package com.auth.domain.param.trans.auth;

import com.auth.common.EntityMapper;
import com.auth.domain.param.auth.RoleInfoParamDO;
import com.auth.param.auth.RoleInfoParamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleInfoParamTrans extends EntityMapper<RoleInfoParamDTO, RoleInfoParamDO> {
}
