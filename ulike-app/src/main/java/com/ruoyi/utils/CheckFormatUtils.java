package com.ruoyi.utils;

import java.util.regex.Pattern;

/**
 * 格式校验工具类
 *
 * @author ruoyi
 */
public class CheckFormatUtils {

    /**
     * 邮箱正则表达式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    /**
     * 手机号正则表达式（中国大陆）
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^1[3-9]\\d{9}$"
    );

    /**
     * 校验字符串是否是邮箱
     *
     * @param email 待校验的邮箱字符串
     * @return true-是邮箱，false-不是邮箱或为空
     */
    public static boolean isEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 校验字符串是否是手机号
     *
     * @param phone 待校验的手机号字符串
     * @return true-是手机号，false-不是手机号或为空
     */
    public static boolean isPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
