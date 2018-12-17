package com.auth.service.impl;

import com.auth.dao.auth.AuthorityInfoMapper;
import com.auth.domain.param.auth.AuthorityInfoParamDO;
import com.auth.domain.param.trans.auth.AuthorityInfoParamTrans;
import com.auth.domain.result.auth.AuthorityInfoDO;
import com.auth.domain.result.trans.AuthorityInfoTrans;
import com.auth.param.auth.AuthorityInfoParamDTO;
import com.auth.result.auth.AuthorityInfoDTO;
import com.auth.service.AuthorityInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorityInfoServiceImpl implements AuthorityInfoService {

    @Resource
    AuthorityInfoMapper authorityInfoMapper;
    @Resource
    AuthorityInfoParamTrans authorityInfoParamTrans;
    @Resource
    AuthorityInfoTrans authorityInfoTrans;
    @Override
    public List<AuthorityInfoDTO> queryAuthorityInfoList(AuthorityInfoParamDTO authorityInfoParamDTO) {
        AuthorityInfoParamDO authorityInfoParamDO=authorityInfoParamTrans.toEntity(authorityInfoParamDTO);
        List<AuthorityInfoDO> authorityInfoDOList=authorityInfoMapper.queryAuthorityList(authorityInfoParamDO);
        List<AuthorityInfoDTO> authorityInfoDTOList=authorityInfoTrans.toDto(authorityInfoDOList);
        return authorityInfoDTOList;
    }
}
