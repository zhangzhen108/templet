package com.auth.domain.result.trans;

import com.auth.common.EntityMapper;
import com.auth.domain.result.auth.RoleInfoDO;
import com.auth.result.auth.RoleInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleInfoTrans extends EntityMapper<RoleInfoDTO, RoleInfoDO> {
}
