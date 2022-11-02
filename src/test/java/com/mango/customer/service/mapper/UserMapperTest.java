package com.mango.customer.service.mapper;

import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

	@InjectMocks private UserMapperImpl service;

	@Test
	public void toEntity() {
		// Arrange
		UserDto user = getUserDto();

		// Act
		User response = service.toEntity(user);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(user.getName(), response.getName());
		Assertions.assertEquals(user.getLastName(), response.getLastName());
		Assertions.assertEquals(user.getCity(), response.getCity());
		Assertions.assertEquals(user.getAddress(), response.getAddress());
	}

	@Test
	public void toDto() {
		// Arrange
		User user = getUser();

		// Act
		UserDto response = service.toDto(user);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(user.getId(), response.getId());
		Assertions.assertEquals(user.getName(), response.getName());
		Assertions.assertEquals(user.getLastName(), response.getLastName());
		Assertions.assertEquals(user.getCity(), response.getCity());
		Assertions.assertEquals(user.getAddress(), response.getAddress());
	}

	private User getUser(){
		User user = new User();
		user.setId(1L);
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
