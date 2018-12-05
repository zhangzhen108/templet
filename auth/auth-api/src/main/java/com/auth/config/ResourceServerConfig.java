package com.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by dujinkai on 2018/8/6.
 * 资源服务配置类
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 不需要认证的请求  http.authorizeRequests()
     *                 //下边的路径放行
     *                 .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
     *                         "/swagger-resources","/swagger-resources/configuration/security",
     *                         "/swagger-ui.html","/course/coursebase/**").permitAll()
     *                 .anyRequest().authenticated();
     * ---------------------
     * 作者：十步杀一人_千里不留行
     * 来源：CSDN
     * 原文：https://blog.csdn.net/m0_37609579/article/details/82846758
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    private static String[] WHITE_LIST = new String[]{"/actuator/health","/v2/api-docs"};

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 前后端分离 不需要csrf
        http.cors().and().csrf().disable()
                //配置order访问控制，必须认证过后才可以访问
                .authorizeRequests().antMatchers(WHITE_LIST).permitAll().anyRequest().authenticated().and();
    }
}
