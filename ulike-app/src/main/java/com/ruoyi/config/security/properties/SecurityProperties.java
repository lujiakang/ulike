package com.ruoyi.config.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/04
 */
@Component
@ConfigurationProperties(prefix = "com.ulike")
public class SecurityProperties {

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }

}