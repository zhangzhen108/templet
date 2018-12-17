package com.auth.domain.result.trans;

import com.auth.common.EntityMapper;
import com.auth.domain.result.auth.RoleAuthorityDO;
import com.auth.result.auth.RoleAuthorityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleAuthorityTrans extends EntityMapper<RoleAuthorityDTO, RoleAuthorityDO> {
}
