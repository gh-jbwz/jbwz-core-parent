package com.jbwz.core.security.service;

import com.jbwz.core.common.base.ResponseCode;
import com.jbwz.core.common.constant.UserConstant;
import com.jbwz.core.common.util.DateUtil;
import com.jbwz.core.security.authentication.FormLoginAuthenticationToken;
import com.jbwz.core.security.common.FormDataVO;
import com.jbwz.core.security.common.LoginType;
import com.jbwz.core.security.common.SessionUser;
import com.jbwz.core.security.common.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Set;

/**
 * 查询登陆用户,设置用登陆状态
 */
public abstract class BaseUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUserDetailsService.class);

    //最大错误登陆次数
    private static final int DEFAULT_MAX_ERROR_TIMES = 5;
    //登录超过最大错误次数后锁定时间,默认10分钟 单位/min
    private static final int DEFAULT_LOCK_TIME = 10;

    private int maxErrorTimes = DEFAULT_MAX_ERROR_TIMES;
    private int lockTime = DEFAULT_LOCK_TIME;

    /**
     * @see #loadUserByToken(FormLoginAuthenticationToken)
     */
    @Deprecated
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 不要重写这个方法
     * (@see)等同于本方法
     *
     * @return SessionUser
     * @see #doLoadUserByToken(FormLoginAuthenticationToken token)
     */
    public UserDetails loadUserByToken(FormLoginAuthenticationToken token) throws UsernameNotFoundException {
        SessionUser userDetails = (SessionUser) loadUser(token.getName(), token.getLoginType());
        if (userDetails == null) {
            userDetails = (SessionUser) doLoadUserByToken(token);
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("没有这个用户");
        }
        userDetails.setAuthorities(getGrantedAuthorities(userDetails));
        //判断用户状态
        checkStatus(userDetails);
        return userDetails;
    }

    /**
     * 通过用户名和登陆类型实现查询用户
     *
     * @param username
     * @param loginType
     * @return SessionUser
     */
    protected abstract UserDetails loadUser(String username, LoginType loginType) throws UsernameNotFoundException;

    /**
     * 通过token查询用户
     *
     * @param token
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails doLoadUserByToken(FormLoginAuthenticationToken token) throws UsernameNotFoundException {
        return null;
    }

    protected void checkStatus(SessionUser sessionUser) {
        String status = sessionUser.getStatus();
        if (status != null) {
            if (UserConstant.ACCOUNT_LOCKED.equals(status) && !shouldUnlockAccount(sessionUser)) {
                sessionUser.setAccountNonLocked(true);
            } else if (UserConstant.ACCOUNT_DISABLE.equals(status)) {
                sessionUser.setEnabled(false);
            }
        }
    }

    public Object loginSuccessHandle(FormDataVO formDataVO) {
        updateLoginTimeAndErrorTimes(DateUtil.nowDate(), 0, getCurrentUserId());
        return doLoginSuccess(formDataVO);
    }

    public void loginErrorHandle(int errorCode, FormDataVO formDataVO) {
        if (errorCode == ResponseCode.ACCOUNT_WRONG_PASSWORD) {
            int errorTimes = getSessionUser().getLoginErrorTimes();
            errorTimes++;
            updateLoginTimeAndErrorTimes(DateUtil.nowDate(), errorTimes, getCurrentUserId());
            if (errorTimes == getMaxErrorTimes()) {
                updateAccountStatus(UserConstant.ACCOUNT_LOCKED, getCurrentUserId());
            }
        }
        doLoginError(formDataVO);
    }

    /**
     * 获取用户的所有角色
     *
     * @param sessionUser 根据用户id查询出用户权限
     * @return
     */
    protected abstract Set<GrantedAuthority> getGrantedAuthorities(SessionUser sessionUser);


    protected abstract void updateAccountStatus(String status, Long currentUserId);

    protected abstract void updateLoginTimeAndErrorTimes(Date date, int errorTimes,
                                                         Long currentUserId);

    /**
     * 登陆成功后操作
     *
     * @param formDataVO
     * @return
     */
    protected abstract Object doLoginSuccess(FormDataVO formDataVO);

    /**
     * 登陆失败后操作
     *
     * @param formDataVO
     */
    protected abstract void doLoginError(FormDataVO formDataVO);


    private boolean shouldUnlockAccount(SessionUser sessionUser) {
        Date lastDate = sessionUser.getLastLoginTime();
        Date nowDate = DateUtil.nowDate();
        boolean b = ((nowDate.getTime() - lastDate.getTime()) / 1000 / 60 / 60) >= getLockTime();
        if (b) {
            updateAccountStatus(UserConstant.ACCOUNT_ENABLE, getCurrentUserId());
        }
        return b;
    }


    protected int getLockTime() {
        return this.lockTime;
    }

    protected int getMaxErrorTimes() {
        return this.maxErrorTimes;
    }


    private SessionUser getSessionUser() {
        SessionUser sessionUser = SessionUtils.getSessionUser();
        return sessionUser;
    }

    private Long getCurrentUserId() {
        SessionUser sessionUser = SessionUtils.getSessionUser();
        return sessionUser.getUserId();
    }

}
