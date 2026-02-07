package com.ruoyi.config.security.token;

import lombok.Data;

import java.io.Serializable;

/**
 * TokenCredentials
 *
 * @author for2cold
 * @date 2018/10/18 11:17
 */
@Data
public class TokenCredentials implements Serializable {

    private String username;

    private String password;

    private String platform;

    private String appVersion;

}
