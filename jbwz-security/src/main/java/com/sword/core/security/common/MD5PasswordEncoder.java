package com.jbwz.core.security.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MD5PasswordEncoder implements PasswordEncoder {
    private final static String salt = "md5pwd";

    @Override
    public String encode(CharSequence rawPassword) {
        String rawpwd = "";
        if (rawPassword != null) {
            rawpwd = rawPassword.toString() + salt;
        }
        return DigestUtils.md5Hex(rawpwd);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
