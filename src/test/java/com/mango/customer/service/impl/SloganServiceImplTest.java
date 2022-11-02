package com.mango.customer.service.impl;

import com.mango.customer.handler.exceptions.SloganLimitException;
import com.mango.customer.model.Slogan;
import com.mango.customer.model.User;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.mapper.SloganMapper;
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
public class SloganServiceImplTest {

	@InjectMocks
	private SloganServiceImpl service;

	@Mock
	private UserRepository userRepository;
	@Mock
	private SloganRepository sloganRepository;

	@Mock
	private SloganMapper mapper;

	@Test
	public void createSlogan_with_count_1_Ok() {
		// Arrange
		User user = getUser();
		SloganDto slogan = getSloganDto();

		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		when(sloganRepository.countByUserId(anyLong())).thenReturn(1);
		when(mapper.toEntity(any(SloganDto.class))).thenReturn(getSlogan());
		when(sloganRepository.save(any(Slogan.class))).thenReturn(getSlogan());
		when(mapper.toDto(any(Slogan.class))).thenReturn(getSloganDto());

		// Act
		SloganDto response = service.createSlogan(getSloganDto());

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(slogan.getId(), response.getId());
		Assertions.assertEquals(slogan.getDescription(), response.getDescription());
		Assertions.assertEquals(slogan.getUserId(), response.getUserId());
	}

	@Test
	public void createSlogan_with_count_3_throws_slogan_limit_exception() {
		// Arrange
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(getUser()));
		when(sloganRepository.countByUserId(anyLong())).thenReturn(3);

		// Act
		SloganLimitException exception =
			Assertions.assertThrows(SloganLimitException.class,
				() -> service.createSlogan(getSloganDto()));

		// Assert
		Assertions.assertNotNull(exception);
	}

	@Test
	public void createSlogan_user_not_found() {
		// Arrange
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

		// Act
		SloganDto response = service.createSlogan(getSloganDto());

		// Assert
		Assertions.assertNull(response);
	}

	private User getUser() {
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
