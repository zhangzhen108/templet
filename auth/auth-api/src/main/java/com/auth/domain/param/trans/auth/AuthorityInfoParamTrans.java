package com.auth.domain.param.trans.auth;

import com.auth.common.EntityMapper;
import com.auth.domain.param.auth.AuthorityInfoParamDO;
import com.auth.param.auth.AuthorityInfoParamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityInfoParamTrans extends EntityMapper<AuthorityInfoParamDTO, AuthorityInfoParamDO> {
}
