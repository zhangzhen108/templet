package com.auth.service;

import com.auth.param.auth.UserInfoParamDTO;
import com.auth.result.auth.UserInfoDTO;

public interface UserInfoService {

    /**
     * 查询用户
     * @param userInfoParamDTO
     * @return
     */
    public UserInfoDTO queryUser(UserInfoParamDTO userInfoParamDTO);

    /**
     * 添加用户
     */
    public void insert(UserInfoDTO userInfoDTO);


}
