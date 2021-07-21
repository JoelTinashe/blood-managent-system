package com.digitalkrapht.bloodbank.bloodbank.utils.auth.config;

import com.digitalkrapht.bloodbank.bloodbank.security.user.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
@Configuration
public class CustomAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !((org.springframework.security.core.Authentication) authentication).isAuthenticated()) {
            return null;
        }
   return null;
        //     return Optional.of(((UserPrincipal) authentication.getPrincipal()).getUserId());
    }
}

