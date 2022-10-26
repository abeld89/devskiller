package com.mango.customer.service;

import com.mango.customer.model.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Long userId);

}
