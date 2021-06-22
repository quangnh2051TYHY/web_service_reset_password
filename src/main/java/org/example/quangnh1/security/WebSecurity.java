package org.example.quangnh1.security;

import org.example.quangnh1.repository.UserRepository;
import org.example.quangnh1.security.constant.SecurityConstant;
import org.example.quangnh1.security.filter.CustomAuthenticationFilter;
import org.example.quangnh1.security.filter.CustomAuthorizationFilter;
import org.example.quangnh1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

  private UserRepository userRepository;

  public WebSecurity(UserService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
    this.userDetailsService = userDetailService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
        .csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, userPrefix + SecurityConstant.SIGN_UP_URL)
        .permitAll()
        .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("SUPER_ADMIN")
//        .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("SUPER_ADMIN","ADMIN") //example about hasAnyRole
//        .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("DELETE_AUTHORITY","CREATE_AUTHORITY") //example about hasAnyAuthority
//                .anyRequest().authenticated().and().addFilter(new CustomAuthenticationFilter(authenticationManager()));
        .anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
        .addFilter(new CustomAuthorizationFilter(authenticationManager(), userRepository));
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
