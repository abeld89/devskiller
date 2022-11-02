package com.mango.customer.service.controller;

import com.mango.customer.controller.UserController;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks private UserController controller;

	@Mock private UserService service;

	@Test
	public void createUserOk() {
		// Arrange
		UserDto userDto = getUserDto();
		when(service.createUser(any(UserDto.class))).thenReturn(userDto);

		// Act
		ResponseEntity<UserDto> response = controller.createUser(userDto);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(userDto.getName(), response.getBody().getName());
		Assertions.assertEquals(userDto.getAddress(), response.getBody().getAddress());
		Assertions.assertEquals(userDto.getCity(), response.getBody().getCity());
		Assertions.assertEquals(userDto.getLastName(), response.getBody().getLastName());
	}

	@Test
	public void updateUserOk() {
		// Arrange
		UserDto userDto = getUserDto();
		when(service.updateUser(any(UserDto.class), eq(1L))).thenReturn(userDto);

		// Act
		ResponseEntity<UserDto> response = controller.updateUser(userDto, 1L);

		// Assert
		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(userDto.getName(), response.getBody().getName());
		Assertions.assertEquals(userDto.getAddress(), response.getBody().getAddress());
		Assertions.assertEquals(userDto.getCity(), response.getBody().getCity());
		Assertions.assertEquals(userDto.getLastName(), response.getBody().getLastName());
	}

	@Test
	public void updateUser_not_found() {
		// Arrange
		UserDto userDto = getUserDto();
		when(service.updateUser(any(UserDto.class), eq(1L))).thenReturn(null);

		// Act
		ResponseEntity<UserDto> response = controller.updateUser(userDto, 1L);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertNull(response.getBody());
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
