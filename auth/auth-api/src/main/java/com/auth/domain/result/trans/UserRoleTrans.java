package com.auth.domain.result.trans;

import com.auth.common.EntityMapper;
import com.auth.domain.result.auth.UserRoleDO;
import com.auth.result.auth.UserRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleTrans extends EntityMapper<UserRoleDTO, UserRoleDO> {
}
