package org.example.quangnh1.repository;

import org.example.quangnh1.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
  Authority findByName(String name);
}
