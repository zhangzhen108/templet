package com.auth.domain.result.trans;

import com.auth.common.EntityMapper;
import com.auth.domain.result.auth.UserInfoDO;
import com.auth.result.auth.UserInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoTrans extends EntityMapper<UserInfoDTO, UserInfoDO> {


}
