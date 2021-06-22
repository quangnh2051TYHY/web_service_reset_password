package org.example.quangnh1.service.impl;

import org.example.quangnh1.entity.Authority;
import org.example.quangnh1.exception.UserServiceException;
import org.example.quangnh1.listener.StartProjectListener;
import org.example.quangnh1.model.response.MessageConstant;
import org.example.quangnh1.repository.AuthorityRepository;
import org.example.quangnh1.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public Authority createAuthority(String authorityName) {
    Authority authority = authorityRepository.findByName(authorityName);

    if (authority == null) {
      authority = new Authority(authorityName);
      authorityRepository.save(authority);
      logger.info("Create authority successfully");
    }

    return authority;
  }

}
