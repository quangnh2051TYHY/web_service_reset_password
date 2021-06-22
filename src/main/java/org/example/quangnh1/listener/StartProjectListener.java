package org.example.quangnh1.listener;

import org.example.quangnh1.entity.Authority;
import org.example.quangnh1.entity.Role;
import org.example.quangnh1.entity.User;
import org.example.quangnh1.model.response.MessageConstant;
import org.example.quangnh1.repository.UserRepository;
import org.example.quangnh1.service.AuthorityService;
import org.example.quangnh1.service.IUserDtoBuilder;
import org.example.quangnh1.service.RoleService;
import org.example.quangnh1.service.UserService;
import org.example.quangnh1.shared.UserDto;
import org.example.quangnh1.shared.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class StartProjectListener {

  @Autowired
  private UserService userService;
  @Autowired
  private AuthorityService authorityService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private Utils utils;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  Logger logger = LoggerFactory.getLogger(StartProjectListener.class);

  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) throws Exception {
    logger.info("Event when Application ready: ahihi.....");
    //Run code to create Authorities
    Authority readAuth = authorityService.createAuthority("READ_AUTHORITY");
    Authority writeAuth = authorityService.createAuthority("WRITE_AUTHORITY");
    Authority deleteAuth = authorityService.createAuthority("DELETE_AUTHORITY");
    Authority createAuth = authorityService.createAuthority("CREATE_AUTHORITY");
    //run code create Roles
    Role roleSuperAdmin = roleService.createRole("ROLE_SUPER_ADMIN", Arrays.asList(createAuth, readAuth, writeAuth, deleteAuth));
    Role roleAdmin = roleService.createRole("ROLE_ADMIN", Arrays.asList(readAuth, writeAuth, deleteAuth));
    Role roleUser = roleService.createRole("ROLE_USER", Arrays.asList(readAuth, writeAuth));

    if (roleSuperAdmin == null) {
      return;
    }
    //run code to create Admin user
    UserDto userDto = new impl.IUserDtoBuilderImpl()
        .setFirstName("Ngo")
        .setLastName(" Hong Quang")
        .setEmail("quangnhse05858@gmail.com")
        .setPassword("hongduy")
        .setEmailVerificationStatus(false)
        .build();
    User adminUser = new User();
    BeanUtils.copyProperties(userDto, adminUser);
    adminUser.setRoles(Arrays.asList(roleSuperAdmin));
    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    adminUser.setUserId(utils.generateUserId(40));
    if(userRepository.findByEmail("quangnhse05858@gmail.com") == null) {
      userRepository.save(adminUser);
    }
  }
}
