package com.auth.service.impl;

import com.auth.dao.auth.RoleAuthorityMapper;
import com.auth.domain.param.auth.RoleAuthorityParamDO;
import com.auth.domain.param.trans.auth.RoleAuthorityParamTrans;
import com.auth.domain.result.auth.RoleAuthorityDO;
import com.auth.domain.result.trans.RoleAuthorityTrans;
import com.auth.param.auth.RoleAuthorityParamDTO;
import com.auth.result.auth.RoleAuthorityDTO;
import com.auth.service.RoleAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    @Resource
    RoleAuthorityMapper roleAuthorityMapper;
    @Resource
    RoleAuthorityParamTrans roleAuthorityParamTrans;
    @Resource
    RoleAuthorityTrans roleAuthorityTrans;
    @Override
    public List<RoleAuthorityDTO> queryAuthorityInfoList(RoleAuthorityParamDTO roleAuthorityParamDTO) {
        RoleAuthorityParamDO roleAuthorityParamDO=roleAuthorityParamTrans.toEntity(roleAuthorityParamDTO);
        List<RoleAuthorityDO> authorityInfoDOList=roleAuthorityMapper.queryRoleAuthorityList(roleAuthorityParamDO);
        List<RoleAuthorityDTO> authorityInfoDTOList=roleAuthorityTrans.toDto(authorityInfoDOList);
        return authorityInfoDTOList;
    }
}
