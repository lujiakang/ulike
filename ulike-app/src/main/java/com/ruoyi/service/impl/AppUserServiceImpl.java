package com.ruoyi.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.service.AppUserService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/04
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public String selectPasswdByEmail(String username) {
        return null;
    }

    @Override
    public String selectPasswdByPhone(String username) {
        return null;
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
