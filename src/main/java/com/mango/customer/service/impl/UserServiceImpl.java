package com.mango.customer.service.impl;

import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.UserService;
import com.mango.customer.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {
		return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto userDto, Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()){
			User userToSave = userMapper.toEntity(userDto);
			userToSave.setId(userId);
			return userMapper.toDto(userRepository.save(userToSave));
		}
		return null;
	}
}
