package impl;


import org.example.quangnh1.entity.Role;
import org.example.quangnh1.service.IUserDtoBuilder;
import org.example.quangnh1.shared.UserDto;

import java.util.Collection;

public class IUserDtoBuilderImpl implements IUserDtoBuilder {
  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String encryptedPassword;
  private String emailVerificationToken;
  private Boolean emailVerificationStatus;

  @Override
  public IUserDtoBuilder setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  @Override
  public IUserDtoBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  @Override
  public IUserDtoBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  @Override
  public IUserDtoBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public IUserDtoBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public IUserDtoBuilder setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
    return this;
  }

  @Override
  public IUserDtoBuilder setEmailVerificationToken(String emailVerificationToken) {
    this.emailVerificationToken = emailVerificationToken;
    return this;
  }

  @Override
  public IUserDtoBuilder setEmailVerificationStatus(Boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
    return this;
  }

  @Override
  public UserDto build() {
    return new UserDto(userId, firstName, lastName, email, password, encryptedPassword,emailVerificationToken, emailVerificationStatus);
  }
}
