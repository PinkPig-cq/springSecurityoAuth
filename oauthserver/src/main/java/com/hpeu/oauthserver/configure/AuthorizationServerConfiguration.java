package com.hpeu.oauthserver.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 请求code的uri
     * @client_id 访问端的ID
     * @response_type 请求Code
     */
    //http://localhost:8080/oauth/authorize?client_id=Client_ID&response_type=code

    /**
     * 请求Token的URI
     * @grant_type oAuth2 的授权模式  笔记上面的4种
     * @code 上面请求到的Code
     * @client 访问的ID Client_ID
     * @secret 由访问ID生成的机密标识 Client_Secret,这个需要加密存JDBC,不加密也要用不加密的算法
     */
    //http://Client_ID:Client_Secret@https://localhost:8080/oauth/token?grant_type=authorization_code&code=uLDklJ

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                //存在内存中
                .inMemory()
                //Client_ID  访问端是谁
                .withClient("Client_ID")
                //根据Client_ID生成的一个标识，用于获取Refresh_Token
                .secret("Client_secret")
                .authorizedGrantTypes("authorization_code")
                .scopes("app")
                .redirectUris("www.baidu.com");
    }
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        //https://projects.spring.io/spring-security-oauth/docs/oauth2.html  看官方文档
//        endpoints.pathMapping("/oauth/authorize","/oauth/authorize222");
//    }
}
