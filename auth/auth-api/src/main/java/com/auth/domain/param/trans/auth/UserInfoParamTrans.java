package com.auth.domain.param.trans.auth;

import com.auth.common.EntityMapper;
import com.auth.domain.param.auth.UserInfoParamDO;
import com.auth.param.auth.UserInfoParamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoParamTrans extends EntityMapper<UserInfoParamDTO,UserInfoParamDO> {


}
