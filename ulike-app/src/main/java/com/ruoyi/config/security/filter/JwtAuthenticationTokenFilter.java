package com.ruoyi.config.security.filter;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.config.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenStore tokenStore;

    private static final List<String> EXCLUDE_URLS = Arrays.asList(
        "/login",
        "/oauth/token"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        String requestURI = request.getRequestURI();

        // 跳过不需要验证token的路径
        if (EXCLUDE_URLS.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 获取 Authorization header
        String bearerToken = request.getHeader("Authorization");
        boolean authenticated = false;

        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);

            // 优先尝试 OAuth2 token 验证
            OAuth2Authentication auth = null;
            try {
                auth = tokenStore.readAuthentication(token);
                if (auth != null && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    authenticated = true;
                }
            } catch (Exception e) {
                // OAuth2 token 验证失败,尝试 JWT
            }

            // 如果 OAuth2 验证失败,尝试 JWT token
            if (!authenticated) {
                LoginUser loginUser = tokenService.getLoginUser(request);
                if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
                {
                    tokenService.verifyToken(loginUser);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    authenticated = true;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
