package org.example.quangnh1.controller;

import org.example.quangnh1.exception.UserServiceException;
import org.example.quangnh1.model.request.UserDetailRequestModel;
import org.example.quangnh1.model.response.MessageConstant;
import org.example.quangnh1.model.response.UserRest;
import org.example.quangnh1.service.UserService;
import org.example.quangnh1.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "get-user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public UserRest getUser(@PathVariable("id") String id) throws Exception {
        UserRest userRest = new UserRest();

        UserDto userDto = userService.getUserById(id);

        BeanUtils.copyProperties(userDto, userRest);
        return userRest;
    }

    @PostMapping("create-user")
    public UserRest createUsers(@RequestBody UserDetailRequestModel userDetails) throws Exception {
        UserRest userRest = new UserRest();
        if(userDetails.getFirstName() == null || userDetails.getFirstName().isEmpty()) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, userRest);
        return userRest;
    }

    @PutMapping("/update-user/{id}")
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailRequestModel userDetail) {
        if(userDetail == null) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        if(userDetail.getFirstName() == null || userDetail.getFirstName().isEmpty()) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        if(userDetail.getEmail() == null || userDetail.getEmail().isEmpty()) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        if(userDetail.getLastName() == null || userDetail.getLastName().isEmpty()) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        if(userDetail.getFirstName() == null || userDetail.getPassword().isEmpty()) {
            throw new UserServiceException(MessageConstant.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        return userService.updateUser(id, userDetail);
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete was called";
    }
}
