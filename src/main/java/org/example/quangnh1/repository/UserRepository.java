package org.example.quangnh1.repository;

import org.example.quangnh1.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);

    User findByUserId(String userId);
}
