package com.jbwz.core.security.dao;

import com.jbwz.core.security.service.BaseUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

public class CustomDaoAuthenticationConfigurer<B extends ProviderManagerBuilder<B>, U extends UserDetailsService>
        extends UserDetailsAwareConfigurer<B, U> {

    private CustomDaoAuthenticationProvider provider = new CustomDaoAuthenticationProvider();

    private final U userDetailsService;

    /**
     * Creates a new instance
     *
     * @param userDetailsService
     */
    public CustomDaoAuthenticationConfigurer(U userDetailsService) {
        Assert.notNull(userDetailsService, "必须有一个类继承 BaseUserDetailsService 并注入为SpringBean");
        this.userDetailsService = userDetailsService;
        provider.setUserDetailsService((BaseUserDetailsService) userDetailsService);
    }


    /**
     * Adds an {@link ObjectPostProcessor} for this class.
     *
     * @return the {@link CustomDaoAuthenticationConfigurer} for further customizations
     */
    @SuppressWarnings("unchecked")
    public CustomDaoAuthenticationConfigurer withObjectPostProcessor(
            ObjectPostProcessor<?> objectPostProcessor) {
        addObjectPostProcessor(objectPostProcessor);
        return this;
    }

    /**
     * Allows specifying the {@link PasswordEncoder} to use with the {@link
     * DaoAuthenticationProvider}. The default is to use plain text.
     *
     * @param passwordEncoder The {@link PasswordEncoder} to use.
     */
    public CustomDaoAuthenticationConfigurer passwordEncoder(PasswordEncoder passwordEncoder) {
        provider.setPasswordEncoder(passwordEncoder);
        return this;
    }


    @Override
    public void configure(B builder) {
        provider = postProcess(provider);
        builder.authenticationProvider(provider);
    }

    @Override
    public U getUserDetailsService() {
        return this.userDetailsService;
    }
}
