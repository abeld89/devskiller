package com.mango.customer.service;

import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.impl.UserServiceImpl;
import com.mango.customer.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks private UserServiceImpl service;

	@Mock private UserRepository repository;

	@Mock private UserMapper mapper;

	@Test
	public void createUserOk() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(getUser()));
		when(mapper.toEntity(any(UserDto.class))).thenReturn(getUser());
		when(repository.save(any(User.class))).thenReturn(getUser());
		when(mapper.toDto(any(User.class))).thenReturn(getUserDto());

		UserDto response = service.createUser(getUserDto());

		Assertions.assertNotNull(response);
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
