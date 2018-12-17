package com.auth.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资源服务配置类
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);
    /**
     * 不需要认证的请求  http.authorizeRequests()
     *                 //下边的路径放行
     *                 .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
     *                         "/swagger-resources","/swagger-resources/configuration/security",
     *                         "/swagger-ui.html","/course/coursebase/**").permitAll()
     *                 .anyRequest().authenticated();
     */
    private static String[] WHITE_LIST = new String[]{"/actuator/health","/v2/api-docs"};
    @Autowired
    private ResourceServerProperties resourceServerProperties;
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //设置用于解码的非对称加密的公钥
        converter.setVerifierKey(getPubKey());
        return converter;
    }
    /**
     * 本地获取公钥
     * @return
     */
    private String getPubKey() {
        log.info("ResourceServerConfig#getPubKey从本地获取公钥");
        Resource resource = new ClassPathResource("public.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            System.out.println("本地公钥");
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            log.info("ResourceServerConfig#getPubKey从本地获取公钥发生异常,异常信息为", ioe);
            return getKeyFromAuthorizationServer();
        }
    }

    /**
     * 授权中心获取公钥
     * @return
     */
    private String getKeyFromAuthorizationServer() {
        log.info("ResourceServerConfig#getKeyFromAuthorizationServer从授权中心获取公钥");
        ObjectMapper objectMapper = new ObjectMapper();
        String pubKey = new RestTemplate().getForObject(resourceServerProperties.getJwt().getKeyUri(), String.class);
        try {
            Map map = objectMapper.readValue(pubKey, Map.class);
            log.info("ResourceServerConfig#getKeyFromAuthorizationServer从授权中心获取公钥为{}", JSON.toJSONString(map));
            return map.get("value").toString();
        } catch (IOException e) {
            log.info("ResourceServerConfig#getKeyFromAuthorizationServer从授权中心获取公钥发生异常,异常信息为", e);
        }
        return null;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 前后端分离 不需要csrf
        http.cors().and().csrf().disable()
                //配置order访问控制，必须认证过后才可以访问
                .authorizeRequests().antMatchers(WHITE_LIST).permitAll().anyRequest().authenticated().and();
    }

}
