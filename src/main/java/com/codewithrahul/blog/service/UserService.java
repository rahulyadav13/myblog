package com.codewithrahul.blog.service;

import com.codewithrahul.blog.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);

    void deleteUser(long id);

    UserDto updateUser(long id, UserDto userDto);

    UserDto getUserById(long id);

    List<UserDto> getUser(int pageNo,int pageSize);
}
