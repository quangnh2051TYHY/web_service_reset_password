package org.example.quangnh1.security;

import org.example.quangnh1.entity.Authority;
import org.example.quangnh1.entity.Role;
import org.example.quangnh1.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserPrinciple implements UserDetails {
  private User user;

  public CustomUserPrinciple() {
  }

  public CustomUserPrinciple(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    Collection<Role> roles = user.getRoles();
    List<Authority> authorityOfEntity = new ArrayList<>();

    if (roles == null) {
      return authorities;
    }
    roles.forEach((role) -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
      authorityOfEntity.addAll(role.getAuthorities());
    });

    authorityOfEntity.forEach((authority) ->
    {
      authorities.add(new SimpleGrantedAuthority(authority.getName()));
    });
    System.out.println("size" + authorities.size());
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.user.getEncryptedPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
