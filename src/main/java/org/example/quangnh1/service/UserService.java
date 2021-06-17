package org.example.quangnh1.service;

import org.example.quangnh1.model.request.UserDetailRequestModel;
import org.example.quangnh1.model.response.UserRest;
import org.example.quangnh1.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto) throws Exception;

    UserDto getUserById(String id) throws Exception;

    UserRest updateUser(String userId, UserDetailRequestModel userDetail);
}
