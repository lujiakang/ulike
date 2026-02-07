package com.ruoyi.service;

import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/04
 */
public interface AppUserService {
    String selectPasswdByEmail(String username);

    String selectPasswdByPhone(String username);

    SysUser selectUserByUserName(String userName);
}
