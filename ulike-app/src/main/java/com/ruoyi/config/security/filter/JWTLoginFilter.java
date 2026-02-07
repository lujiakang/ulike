package com.ruoyi.config.security.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.config.security.MyAuthenticationSuccessHandler;
import com.ruoyi.config.security.context.AuthenticationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/05
 */
@Slf4j
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager, MyAuthenticationSuccessHandler successHandler) {
        super(new AntPathRequestMatcher(url, null));
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(successHandler);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
           /* JSONObject paramJsonObject = JSON.parseObject(req.getInputStream(), JSONObject.class);
            if(parameterMap.isEmpty()){
                throw new ServiceException("无效请求", HttpStatus.UNAUTHORIZED);
            }*/
            String[] usernames = parameterMap.get("username");
            if(usernames == null || usernames.length == 0){
                throw new ServiceException("username 不能为空", HttpStatus.UNAUTHORIZED);
            }
            String username = usernames[0];
            String[] passwords = parameterMap.get("password");
            if(passwords == null || passwords.length == 0){
                throw new ServiceException("password 不能为空", HttpStatus.UNAUTHORIZED);
            }
            String password = passwords[0];
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            return getAuthenticationManager().authenticate(authenticationToken);
        } catch (Exception e) {
            log.error("登录失败", e);
            throw new ServiceException("登录失败");
        }
    }
}
