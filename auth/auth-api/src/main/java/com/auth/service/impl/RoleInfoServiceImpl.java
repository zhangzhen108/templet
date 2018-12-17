package com.auth.service.impl;

import com.auth.dao.auth.RoleInfoMapper;
import com.auth.domain.param.auth.RoleInfoParamDO;
import com.auth.domain.param.trans.auth.RoleInfoParamTrans;
import com.auth.domain.result.auth.RoleInfoDO;
import com.auth.domain.result.trans.RoleInfoTrans;
import com.auth.param.auth.RoleInfoParamDTO;
import com.auth.result.auth.RoleInfoDTO;
import com.auth.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    RoleInfoMapper roleInfoMapper;
    @Resource
    RoleInfoParamTrans roleInfoParamTrans;
    @Resource
    RoleInfoTrans roleInfoTrans;
    @Override
    public List<RoleInfoDTO> queryRoleInfoList(RoleInfoParamDTO roleInfoParamDTO) {
        RoleInfoParamDO roleInfoParamDO=roleInfoParamTrans.toEntity(roleInfoParamDTO);
        List<RoleInfoDO> roleInfoDOList=roleInfoMapper.queryRoleInfoList(roleInfoParamDO);
        List<RoleInfoDTO> roleInfoDTOList=roleInfoTrans.toDto(roleInfoDOList);
        return roleInfoDTOList;
    }
}
