package com.jbwz.core.security.config;

import com.jbwz.core.security.authorization.AuthorizationRoleResourceService;
import com.jbwz.core.security.authorization.DynamicRoleVoter;
import com.jbwz.core.security.common.MD5PasswordEncoder;
import com.jbwz.core.security.dao.CustomDaoAuthenticationConfigurer;
import com.jbwz.core.security.filter.LogAccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.AntPathMatcher;

import java.util.*;

@EnableWebSecurity
@EnableRedisHttpSession
@Configuration
public class SecurityMVCConfig extends WebSecurityConfigurerAdapter {

    public static final Set<String> ANYONE_ACCESS_URL = new HashSet<>();
    public static final Set<String> STATIC_RESOURCES_URL = new HashSet<>();

    private static AntPathMatcher matcher = new AntPathMatcher();
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserDetailsService userDetailsService;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private AuthorizationRoleResourceService roleResourceService;

    /**
     * 配置不需要安全认证的url
     */
    @Value("${login.security.excludes}")
    private List<String> urls;


    static {
        ANYONE_ACCESS_URL.addAll(StaticPathConfig.STATIC_RESOURCES_URL);
        ANYONE_ACCESS_URL.add("/login");
        ANYONE_ACCESS_URL.add("/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (urls != null) {
            ANYONE_ACCESS_URL.addAll(urls);
        }
        http.csrf().disable()
//                .anonymous().disable()
                .addFilterBefore(new LogAccessFilter(), LogoutFilter.class)
                .authorizeRequests()
                .accessDecisionManager(new AffirmativeBased(Collections.singletonList(new DynamicRoleVoter(roleResourceService))))
                .antMatchers(toArrayString(ANYONE_ACCESS_URL)).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(new FormLoginConfigurer<>())
                .and()
                .sessionManagement().disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.apply(new CustomDaoAuthenticationConfigurer<>(userDetailsService)).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
    }


    private String[] toArrayString(Collection<String> collection) {
        return collection.toArray(new String[collection.size()]);
    }
}
