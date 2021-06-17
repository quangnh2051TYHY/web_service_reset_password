package org.example.quangnh1.security;

import org.example.quangnh1.security.constant.SecurityConstant;
import org.example.quangnh1.security.filter.CustomAuthenticationFilter;
import org.example.quangnh1.security.filter.CustomAuthorizationFilter;
import org.example.quangnh1.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final String userPrefix = "/users";
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, userPrefix + SecurityConstant.SIGN_UP_URL)
                .permitAll()
//                .anyRequest().authenticated().and().addFilter(new CustomAuthenticationFilter(authenticationManager()));
                .anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
                .addFilter(new CustomAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    public CustomAuthenticationFilter getAuthenticationFilter() throws Exception {
        final CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/quangnh1/login");
        return filter;
    }

}
