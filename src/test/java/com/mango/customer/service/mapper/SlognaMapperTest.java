package com.mango.customer.service.mapper;

import com.mango.customer.model.Slogan;
import com.mango.customer.model.User;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SlognaMapperTest {

	@InjectMocks private SloganMapperImpl service;
	@Mock
	private UserRepository userRepository;

	@Test
	public void toEntity() {
		// Arrange
		SloganDto slogan = getSloganDto();
		when(userRepository.getOne(eq(slogan.getUserId()))).thenReturn(getUser());

		// Act
		Slogan response = service.toEntity(slogan);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(slogan.getDescription(), response.getDescription());
		Assertions.assertEquals(slogan.getUserId(), response.getUser().getId());
	}

	@Test
	public void toDto() {
		// Arrange
		Slogan slogan = getSlogan();

		// Act
		SloganDto response = service.toDto(slogan);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(slogan.getDescription(), response.getDescription());
		Assertions.assertEquals(slogan.getId(), response.getId());
		Assertions.assertEquals(slogan.getUser().getId(), response.getUserId());

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

	private Slogan getSlogan() {
		Slogan slogan = new Slogan();
		slogan.setId(1L);
		slogan.setUser(getUser());
		slogan.setDescription("description");
		return slogan;
	}

	private SloganDto getSloganDto() {
		SloganDto slogan = new SloganDto();
		slogan.setId(1L);
		slogan.setUserId(1L);
		slogan.setDescription("description");
		return slogan;
	}
}
