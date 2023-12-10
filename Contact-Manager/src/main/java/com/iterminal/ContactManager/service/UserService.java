package com.iterminal.ContactManager.service;

import com.iterminal.ContactManager.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUser();
    public UserDto getOneUser(long id);
    public UserDto saveUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto , long id);
    public UserDto deleteUser(long id);
}
