package com.auth.config;

import com.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/8/11.
 * 自定义认证
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }


    /**
     * 密码工具类
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 注入管理员服务接口
     */
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 用户输入的用户名
        String name = authentication.getName();

        // 用户输入的密码
        String password = authentication.getCredentials().toString();

        // 根据用户名查询用户信息
        UserDetails userDetails = getUserDetails(name);

        if (Objects.isNull(userDetails)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());
    }

    /**
     * 获得用户信息
     *
     * @param name 用户名称
     * @return 返回用户信息
     */
    private UserDetails getUserDetails(String name) {
        return userDetailsService.loadUserByUsername(name);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
