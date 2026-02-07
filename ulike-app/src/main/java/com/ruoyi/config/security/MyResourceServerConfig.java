package com.ruoyi.config.security;

import com.ruoyi.config.security.filter.JWTLoginFilter;
import com.ruoyi.config.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/04
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登录 方式
        http.formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler);
        http
                .authorizeRequests()
                .antMatchers("/oauth/token","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable();
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter("/login", authenticationManager, myAuthenticationSuccessHandler);
        http.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}