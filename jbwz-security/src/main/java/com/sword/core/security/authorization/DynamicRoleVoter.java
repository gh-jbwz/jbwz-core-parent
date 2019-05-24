package com.jbwz.core.security.authorization;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DynamicRoleVoter implements AccessDecisionVoter<FilterInvocation> {


    private static final String AUTHENTICATED = "authenticated";
    private AuthorizationRoleResourceService roleResourceService;

    public DynamicRoleVoter(AuthorizationRoleResourceService roleResourceService) {
        Assert.notNull(roleResourceService, "必须有一个类实现接口 AuthorizationRoleResourceService 并注入为SpringBean");
        this.roleResourceService = roleResourceService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public boolean requireAuthorize(ConfigAttribute attribute) {
        return AUTHENTICATED.equals(attribute.toString());
    }


    /***
     * 动态判断是否有权限
     * <P>
     *     每个角色都对应多个资源url,同理。
     *     根据request中的url获取对应的多个角色，再根据当前用户有哪些角色判断是否包含url所获取的角色
     * </P>
     * @param authentication
     * @param fi
     * @param attributes
     * @return
     */
    @Override
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ACCESS_DENIED;
        }

        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
        List<GrantedAuthority> urlRoleIds = matchRoleWithRequest(fi.getRequest());
        for (ConfigAttribute attribute : attributes) {
            if (requireAuthorize(attribute)) {
                result = ACCESS_DENIED;
                for (GrantedAuthority authority : authorities) {
                    if (urlRoleIds.contains(authority))
                        return ACCESS_GRANTED;
                }
            } else {
                return ACCESS_GRANTED;
            }
        }
        return result;
    }

    private List<GrantedAuthority> matchRoleWithRequest(HttpServletRequest request) {
        List<Map<AntPathRequestMatcher, List<GrantedAuthority>>> list = roleResourceService.getAllAuthorities();
        List<GrantedAuthority> roles = new ArrayList<>();
        list.forEach(map ->
                map.keySet().forEach(matcher -> {
                    if (matcher.matches(request)) roles.addAll(map.get(matcher));
                })
        );
        return roles;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }

}
