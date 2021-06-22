package org.example.quangnh1.service;

import org.example.quangnh1.entity.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {
  Authority createAuthority(String authorityName);
}
