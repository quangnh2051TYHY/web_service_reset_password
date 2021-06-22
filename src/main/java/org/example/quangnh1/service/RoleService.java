package org.example.quangnh1.service;

import org.example.quangnh1.entity.Authority;
import org.example.quangnh1.entity.Role;
import org.example.quangnh1.model.request.UserDetailRequestModel;
import org.example.quangnh1.model.response.ServiceResult;
import org.example.quangnh1.model.response.UserRest;
import org.example.quangnh1.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RoleService {
  Role createRole(String roleName, Collection<Authority> authorities);
}
