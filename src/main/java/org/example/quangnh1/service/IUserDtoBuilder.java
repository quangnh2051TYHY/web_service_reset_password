package org.example.quangnh1.service;

import org.example.quangnh1.entity.Role;
import org.example.quangnh1.shared.UserDto;

import java.util.Collection;

public interface IUserDtoBuilder {
  IUserDtoBuilder setUserId(String userId);
  IUserDtoBuilder setFirstName(String firstName);
  IUserDtoBuilder setLastName(String lastName);
  IUserDtoBuilder setEmail(String email);
  IUserDtoBuilder setPassword(String password);
  IUserDtoBuilder setEncryptedPassword(String password);
  IUserDtoBuilder setEmailVerificationToken(String emailVerificationToken);
  IUserDtoBuilder setEmailVerificationStatus(Boolean emailVerificationStatus);
  UserDto build();
}
