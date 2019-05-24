package com.jbwz.core.security.authentication;

import com.jbwz.core.security.common.LoginType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FormLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1122223243992L;
    private LoginType loginType;

    public FormLoginAuthenticationToken(Object principal, Object credentials, LoginType loginType) {
        super(principal, credentials);
        this.loginType = loginType;
    }

    public FormLoginAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public FormLoginAuthenticationToken(Object principal, Object credentials,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}

