package org.example.quangnh1.service.impl;

import org.example.quangnh1.entity.Authority;
import org.example.quangnh1.entity.Role;
import org.example.quangnh1.repository.AuthorityRepository;
import org.example.quangnh1.repository.RoleRepository;
import org.example.quangnh1.service.AuthorityService;
import org.example.quangnh1.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

  Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role createRole(String roleName, Collection<Authority> authorities) {
    Role role = roleRepository.findByName(roleName);
    if (role == null) {

      role = new Role(roleName);
      role.setAuthorities(authorities);
      roleRepository.save(role);

      logger.info("Create role successfully");
    }
    return role;
  }
}
