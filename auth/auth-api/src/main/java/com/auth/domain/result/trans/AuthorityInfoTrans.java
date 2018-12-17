package com.auth.domain.result.trans;

import com.auth.common.EntityMapper;
import com.auth.domain.result.auth.AuthorityInfoDO;
import com.auth.result.auth.AuthorityInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityInfoTrans extends EntityMapper<AuthorityInfoDTO, AuthorityInfoDO> {
}
