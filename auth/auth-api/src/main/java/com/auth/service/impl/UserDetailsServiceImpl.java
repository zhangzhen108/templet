package com.auth.service.impl;

import com.auth.common.BusinessErrorException;
import com.auth.common.CommonEnum;
import com.auth.common.ErrorCodeEnum;
import com.auth.domain.result.AuthUser;
import com.auth.param.auth.*;
import com.auth.result.auth.*;
import com.auth.service.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    AuthorityInfoService authorityInfoService;
    @Resource
    RoleAuthorityService roleAuthorityService;
    @Resource
    RoleInfoService roleInfoService;
    @Resource
    UserInfoService userInfoService;
    @Resource
    UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return this.getUserDetails(s);
    }
    private UserDetails getUserDetails(String username){
        UserInfoDTO userInfoDTO=this.getUserInfo(username);
        List<UserRoleDTO> userRoleDTOList=this.getUserRoleList(userInfoDTO);
        List<RoleInfoDTO> roleInfoDTOList=this.getRoleList(userRoleDTOList);
        List<RoleAuthorityDTO> roleAuthorityDTOList=this.getRoleAuthorityList(roleInfoDTOList);
        List<AuthorityInfoDTO> authorityInfoDTOList=this.getAuthorityInfoList(roleAuthorityDTOList);
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (CollectionUtils.isEmpty(authorityInfoDTOList)) {
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_AUTHORITY_ERROR);
        }
        for (AuthorityInfoDTO authorityInfoDTO:authorityInfoDTOList) {
            if(authorityInfoDTO==null||authorityInfoDTO.getAuthority()==null){
                continue;
            }
            GrantedAuthority authority=new SimpleGrantedAuthority(authorityInfoDTO.getAuthority());
            grantedAuthorities.add(authority);
        }
        AuthUser userDetailsDO=new AuthUser(userInfoDTO.getUsername(),userInfoDTO.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        userDetailsDO.setRoleInfoDTOList(roleInfoDTOList);
        userDetailsDO.setUserInfoDTO(userInfoDTO);
        return userDetailsDO;
    }

    /**
     * 查询权限
     * @return
     */
    private List<AuthorityInfoDTO> getAuthorityInfoList(List<RoleAuthorityDTO> roleAuthorityDTOList){
        List<Long> authorityInfoList=new ArrayList<>();
        for (RoleAuthorityDTO roleAuthorityDTO:roleAuthorityDTOList) {
            authorityInfoList.add(roleAuthorityDTO.getAuthorityId());
        }
        AuthorityInfoParamDTO authorityInfoParamDTO=new AuthorityInfoParamDTO();
        authorityInfoParamDTO.setAuthorityInfoList(authorityInfoList);
        authorityInfoParamDTO.setStatus(CommonEnum.STATUS.getCode());
        List<AuthorityInfoDTO> authorityInfoDTOList=authorityInfoService.queryAuthorityInfoList(authorityInfoParamDTO);
        if(CollectionUtils.isEmpty(authorityInfoDTOList)){
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_AUTHORITY_ERROR);
        }
        return authorityInfoDTOList;
    }
    /**
     * 查询角色权限关系
     * @return
     */
    private List<RoleAuthorityDTO> getRoleAuthorityList(List<RoleInfoDTO> roleInfoDTOList){
        List<Long> roleIdList=new ArrayList<>();
        for (RoleInfoDTO roleInfoDTO:roleInfoDTOList) {
            roleIdList.add(roleInfoDTO.getId());
        }
        RoleAuthorityParamDTO roleAuthorityParamDTO=new RoleAuthorityParamDTO();
        roleAuthorityParamDTO.setRoleIdList(roleIdList);
        roleAuthorityParamDTO.setStatus(CommonEnum.STATUS.getCode());
        List<RoleAuthorityDTO> roleAuthorityDTOList=roleAuthorityService.queryAuthorityInfoList(roleAuthorityParamDTO);
        if(CollectionUtils.isEmpty(roleInfoDTOList)){
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_AUTHORITY_ERROR);
        }
        return roleAuthorityDTOList;
    }
    /**
     *查询角色
     * @param userRoleDTOList
     * @return
     */
    private List<RoleInfoDTO> getRoleList(List<UserRoleDTO> userRoleDTOList){

        RoleInfoParamDTO roleInfoParamDTO=new RoleInfoParamDTO();
        List<Long> roleIdList=new ArrayList<>();
        for (UserRoleDTO userRoleDTO:userRoleDTOList) {
            roleIdList.add(userRoleDTO.getRoleId());
        }
        roleInfoParamDTO.setRoleIdList(roleIdList);
        roleInfoParamDTO.setStatus(CommonEnum.STATUS.getCode());
        List<RoleInfoDTO> roleInfoDTOList=roleInfoService.queryRoleInfoList(roleInfoParamDTO);
        if(CollectionUtils.isEmpty(roleInfoDTOList)){
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_ROLE_ERROR);
        }
        return roleInfoDTOList;
    }
    /**
     * 查询用户角色关系
     * @param userInfoDTO
     * @return
     */
    private List<UserRoleDTO> getUserRoleList(UserInfoDTO userInfoDTO){
        UserRoleParamDTO userRoleParamDTO=new UserRoleParamDTO();
        userRoleParamDTO.setUserId(userInfoDTO.getId());
        userRoleParamDTO.setStatus(CommonEnum.STATUS.getCode());
        List<UserRoleDTO> userRoleDTOList=userRoleService.queryUserRoleList(userRoleParamDTO);
        if(CollectionUtils.isEmpty(userRoleDTOList)){
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_ROLE_ERROR);
        }
        return userRoleDTOList;
    }
    /**
     * 查询人员
     * @param username
     * @return
     */
    private UserInfoDTO getUserInfo(String username){
        UserInfoParamDTO userInfoParamDTO=new UserInfoParamDTO();
        userInfoParamDTO.setUsername(username);
        userInfoParamDTO.setStatus(CommonEnum.STATUS.getCode());
        UserInfoDTO userInfoDTO=userInfoService.queryUser(userInfoParamDTO);
        if(userInfoDTO==null||userInfoDTO.getId()==null){
            throw new BusinessErrorException(ErrorCodeEnum.NO_USER_ERROR);
        }
        return userInfoDTO;
    }
}
