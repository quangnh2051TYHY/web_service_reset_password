package org.example.quangnh1.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.example.quangnh1.entity.User;
import org.example.quangnh1.exception.UserServiceException;
import org.example.quangnh1.model.request.UserDetailRequestModel;
import org.example.quangnh1.model.response.MessageConstant;
import org.example.quangnh1.model.response.ServiceResult;
import org.example.quangnh1.model.response.UserRest;
import org.example.quangnh1.repository.UserRepository;
import org.example.quangnh1.security.CustomUserPrinciple;
import org.example.quangnh1.service.UserService;
import org.example.quangnh1.shared.UserDto;
import org.example.quangnh1.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Utils utils;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDto createUser(UserDto userDto) throws Exception {
    //need to check UserDto is null
    User storedUser = userRepository.findByEmail(userDto.getEmail());
    if (storedUser != null) throw new UserServiceException(MessageConstant.RECORD_ALREADY_EXISTS.getErrorMessage());
    User user = new User();
    BeanUtils.copyProperties(userDto, user);

    user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    user.setUserId(utils.generateUserId(40));
    userRepository.save(user);

    UserDto returnDto = new UserDto();
    BeanUtils.copyProperties(user, returnDto);
    return returnDto;
  }

  @Override
  public UserDto getUserById(String id) throws Exception {
    User user = userRepository.findByUserId(id);
    if (user == null) {
      throw new UsernameNotFoundException("This user has not exist");
    }
    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(user, userDto);

    return userDto;
  }

  @Override
  public UserDto getUser(String email) {
    User user = userRepository.findByEmail(email);

    if (user == null)
      throw new UsernameNotFoundException(email);

    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(user, userDto);

    return userDto;
  }

  @Override
  public UserRest updateUser(String userId, UserDetailRequestModel userDetail) {
    User userStored = userRepository.findByUserId(userId);
    if (userStored == null) {
      throw new UserServiceException(MessageConstant.NO_RECORD_FOUND.getErrorMessage());
    }
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetail, userDto);

    userStored.setEmail(userDetail.getEmail());
    userStored.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetail.getPassword()));
    userStored.setFirstName(userDto.getFirstName());
    userStored.setLastName(userDto.getLastName());

    userRepository.save(userStored);

    UserRest userRest = new UserRest();
    BeanUtils.copyProperties(userStored, userRest);

    return userRest;
  }

  @Override
  public ServiceResult deleteUserById(String userId) {
    User user = userRepository.findByUserId(userId);

    if (user == null) {
      throw new UserServiceException(MessageConstant.NO_RECORD_FOUND.getErrorMessage());
    }

    userRepository.delete(user);
    return new ServiceResult(null, ServiceResult.STATUS_SUCCESS, "Delete Successfully");
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User userEntity = userRepository.findByEmail(email);
    if (userEntity == null) throw new UsernameNotFoundException("email has not exist");
    return new CustomUserPrinciple(userEntity);
//    return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
//        return new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return new ArrayList<>();
//            }
//
//            @Override
//            public String getPassword() {
//                return userEntity.getEncryptedPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return userEntity.getEmail();
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return false;
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return false;
//            }
//        };
  }
}
