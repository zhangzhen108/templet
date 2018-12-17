package com.auth.service;

import com.auth.param.auth.AuthorityInfoParamDTO;
import com.auth.result.auth.AuthorityInfoDTO;

import java.util.List;

public interface AuthorityInfoService {
    /**
     * 查询权限
     * @param authorityInfoParamDTO
     * @return
     */
    public List<AuthorityInfoDTO> queryAuthorityInfoList(AuthorityInfoParamDTO authorityInfoParamDTO);
}
