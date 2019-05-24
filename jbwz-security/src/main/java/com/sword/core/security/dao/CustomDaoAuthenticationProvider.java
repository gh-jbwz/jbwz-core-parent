package com.jbwz.core.security.dao;

import com.jbwz.core.security.authentication.FormLoginAuthenticationToken;
import com.jbwz.core.security.common.MD5PasswordEncoder;
import com.jbwz.core.security.service.BaseUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/***
 * 拉取用户并校验密码
 */
public class CustomDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    /**
     * 默认使用md5加密
     */
    private PasswordEncoder passwordEncoder;

    /**
     * The password used to perform on when the user is not found to avoid SEC-2056. This is
     * necessary, because some {@link PasswordEncoder} implementations will short circuit if the
     * password is not in a valid format.
     */
    private String userNotFoundEncodedPassword;
    private BaseUserDetailsService baseUserDetailsService;

    public CustomDaoAuthenticationProvider() {
        setHideUserNotFoundExceptions(false);
        setPasswordEncoder(new MD5PasswordEncoder());
    }


    /**
     * 校验密码
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: 没有从数据库得到密码");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: 密码和数据库不匹配");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }

    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.baseUserDetailsService, "A UserDetailsService must be set");
    }

    protected final UserDetails retrieveUser(String username,
                                             UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        this.prepareTimingAttackProtection();

        try {
            BaseUserDetailsService baseUserDetailsService =
                    (BaseUserDetailsService) this.getUserDetailsService();
            UserDetails loadedUser =
                    baseUserDetailsService.loadUserByToken((FormLoginAuthenticationToken) authentication);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            } else {
                return loadedUser;
            }
        } catch (UsernameNotFoundException var4) {
            this.mitigateAgainstTimingAttack(authentication);
            throw var4;
        } catch (InternalAuthenticationServiceException var5) {
            throw var5;
        } catch (CredentialsExpiredException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }
    }

    private void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode("userNotFoundPassword");
        }

    }

    private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }

    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    public void setUserDetailsService(BaseUserDetailsService userDetailsService) {
        this.baseUserDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.baseUserDetailsService;
    }
}

