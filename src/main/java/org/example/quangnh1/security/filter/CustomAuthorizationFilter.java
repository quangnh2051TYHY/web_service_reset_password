package org.example.quangnh1.security.filter;

import io.jsonwebtoken.Jwts;
import org.example.quangnh1.entity.User;
import org.example.quangnh1.repository.UserRepository;
import org.example.quangnh1.security.CustomUserPrinciple;
import org.example.quangnh1.security.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class CustomAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    public CustomAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstant.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_STRING);
        if(token != null) {
            token = token.replace(SecurityConstant.TOKEN_PREFIX, "");

            String user = Jwts.parser()
                    .setSigningKey(SecurityConstant.TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            if( user != null) {
                User existed = userRepository.findByEmail(user);
                CustomUserPrinciple customUserPrinciple = new CustomUserPrinciple(existed);
                return new UsernamePasswordAuthenticationToken(user, null, customUserPrinciple.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
