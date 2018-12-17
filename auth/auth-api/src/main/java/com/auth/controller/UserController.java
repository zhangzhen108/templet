package com.auth.controller;

import com.auth.common.ErrorCodeEnum;
import com.auth.domain.result.auth.UserInfoDO;
import com.auth.result.ResponseDTO;
import com.auth.result.auth.UserInfoDTO;
import com.auth.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
@RequestMapping("api/userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserInfoService userInfoService;
    @GetMapping("/userinfo")
    public Principal user(Principal principal)
    {
        return principal;
    }
    @PostMapping("/registe")
    public void registe(UserInfoDTO userInfoDTO){
        userInfoService.insert(userInfoDTO);
    }
}