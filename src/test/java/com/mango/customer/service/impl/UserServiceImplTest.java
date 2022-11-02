package com.mango.customer.service.impl;

import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks private UserServiceImpl service;

	@Mock private UserRepository repository;

	@Mock private UserMapper mapper;

	@Test
	public void createUserOk() {
		// Arrange
		User user = getUser();
		when(mapper.toEntity(any(UserDto.class))).thenReturn(user);
		when(repository.save(any(User.class))).thenReturn(user);
		when(mapper.toDto(any(User.class))).thenReturn(getUserDto());

		// Act
		UserDto response = service.createUser(getUserDto());

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(user.getId(), response.getId());
		Assertions.assertEquals(user.getName(), response.getName());
		Assertions.assertEquals(user.getLastName(), response.getLastName());
		Assertions.assertEquals(user.getCity(), response.getCity());
		Assertions.assertEquals(user.getAddress(), response.getAddress());
	}

	@Test
	public void updateUserOk() {
		// Arrange
		User user = getUser();
		when(repository.findById(anyLong())).thenReturn(Optional.of(user));
		when(mapper.toEntity(any(UserDto.class))).thenReturn(user);
		when(repository.save(any(User.class))).thenReturn(user);
		when(mapper.toDto(any(User.class))).thenReturn(getUserDto());

		// Act
		UserDto response = service.updateUser(getUserDto(), 1L);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(user.getName(), response.getName());
		Assertions.assertEquals(user.getLastName(), response.getLastName());
		Assertions.assertEquals(user.getCity(), response.getCity());
		Assertions.assertEquals(user.getAddress(), response.getAddress());
	}

	@Test
	public void updateUser_return_null() {
		// Arrange
		User user = getUser();
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		// Act
		UserDto response = service.updateUser(getUserDto(), 1L);

		// Assert
		Assertions.assertNull(response);
	}

	private User getUser(){
		User user = new User();
		user.setCity("city");
		user.setName("nameUser");
		user.setLastName("lastName");
		user.setEmail("email@email.com");
		user.setAddress("address");
		return user;
	}

	private UserDto getUserDto(){
		UserDto user = new UserDto();
		user.setCity("city");
		user.setName("nameUser");
		user.setLastName("lastName");
		user.setEmail("email@email.com");
		user.setAddress("address");
		return user;
	}
}
