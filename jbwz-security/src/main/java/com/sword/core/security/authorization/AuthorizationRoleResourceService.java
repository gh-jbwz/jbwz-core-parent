package com.jbwz.core.security.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

public interface AuthorizationRoleResourceService {
    /**
     * 获取所有的资源和角色的对应关系
     * <p>
     * 需要加缓存，每次请求都要调用
     * </p>
     *
     * @return
     */
    List<Map<AntPathRequestMatcher, List<GrantedAuthority>>> getAllAuthorities();


}
