package org.example.quangnh1.repository;

import org.example.quangnh1.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  Role findByName(String name);
}
