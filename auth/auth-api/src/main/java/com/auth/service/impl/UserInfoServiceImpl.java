package com.auth.service.impl;


import com.auth.common.BusinessErrorException;
import com.auth.common.ErrorCodeEnum;
import com.auth.dao.auth.UserInfoMapper;
import com.auth.domain.param.auth.UserInfoParamDO;
import com.auth.domain.param.trans.auth.UserInfoParamTrans;
import com.auth.domain.result.auth.UserInfoDO;
import com.auth.domain.result.trans.UserInfoTrans;
import com.auth.param.auth.UserInfoParamDTO;
import com.auth.result.auth.UserInfoDTO;
import com.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl  implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    UserInfoParamTrans userInfoParamTrans;
    @Resource
    UserInfoTrans userInfoTrans;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserInfoDTO queryUser(UserInfoParamDTO userInfoParamDTO) {
        UserInfoParamDO userInfoParamDO=userInfoParamTrans.toEntity(userInfoParamDTO);
        UserInfoDO userInfoDO=userInfoMapper.queryUser(userInfoParamDO);
        UserInfoDTO userInfoDTO=userInfoTrans.toDto(userInfoDO);
        return userInfoDTO;
    }

    @Override
    public void insert(UserInfoDTO userInfoDTO) {
        this.checkUserInsert(userInfoDTO);
        String password=bCryptPasswordEncoder.encode(userInfoDTO.getPassword());
        userInfoDTO.setPassword(password);
        UserInfoDO userInfoDO=userInfoTrans.toEntity(userInfoDTO);
        int result=userInfoMapper.insert(userInfoDO);
        if(result==0){
            throw new BusinessErrorException(ErrorCodeEnum.FAIL);
        }
    }

    /**
     * 参数校验
     * @param userInfoDTO
     */
    private void checkUserInsert(UserInfoDTO userInfoDTO){
        if(userInfoDTO==null||userInfoDTO.getPassword()==null||userInfoDTO.getUsername()==null){
            throw new BusinessErrorException(ErrorCodeEnum.CHECKFAIL);
        }
    }
}
