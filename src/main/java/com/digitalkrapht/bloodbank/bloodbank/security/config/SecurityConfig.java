package com.digitalkrapht.bloodbank.bloodbank.security.config;

import com.digitalkrapht.bloodbank.bloodbank.security.jwt.JwtAuthenticationEntryPoint;
import com.digitalkrapht.bloodbank.bloodbank.security.jwt.JwtAuthenticationFilter;
import com.digitalkrapht.bloodbank.bloodbank.security.user.CustomUserDetailsService;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.config.CustomAuditAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());


    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }





    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuditorAware<String> auditorAware() {
        return new CustomAuditAware();
    }


    private static final String[] SWAGGER_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/csrf",
            "/swagger-resources",
            "/**swagger**",
            "/swagger-resources/**",
            "/configuration/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
    private static final String[] FILES_WHITELIST = {
            //=== file extensions
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };

    private static final String[] API_WHITELIST = {
            //=== api exceptions
            "/",

            "/**/avatar/**",
            "/**/ecocashCallbackv5",
            "/**/cassavaPaymentGateway/callback",
            "/**/cassavaPaymentGateway/redirectUrl",
            "/**/api/documents/download/**",
            "/**/api/documents/products/productVariation/image/download/**",
            "/**/callback/refunds",
            "/**/sms/callback",
            "/**/sms/test",
            "/api/ecorec/**",
            "/**/api/users/test/**",
            "/**/simulate",
            "/**/track",
            "/api/users/shopAdmin/view/active",
            "/ws/**",
            "/api/auth/**",
            "/api/users/**",
            "/api/users/backOfficeAgent/create"
    };
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers(FILES_WHITELIST).permitAll().antMatchers(API_WHITELIST).permitAll().antMatchers(SWAGGER_WHITELIST).permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.cors();
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }



}
