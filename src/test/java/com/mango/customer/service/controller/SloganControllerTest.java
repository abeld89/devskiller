package com.mango.customer.service.controller;

import com.mango.customer.controller.SloganController;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.service.SloganService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SloganControllerTest {

	@InjectMocks private SloganController controller;

	@Mock private SloganService service;

	@Test
	public void createSloganOk() {
		// Arrange
		SloganDto sloganDto = getSloganDto();
		when(service.createSlogan(any(SloganDto.class))).thenReturn(sloganDto);

		// Act
		ResponseEntity<SloganDto> response = controller.createSlogan(sloganDto);

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(sloganDto.getUserId(), response.getBody().getUserId());
		Assertions.assertEquals(sloganDto.getDescription(), response.getBody().getDescription());
		Assertions.assertEquals(sloganDto.getId(), response.getBody().getId());
	}

	@Test
	public void createSlogan_not_found() {
		// Arrange
		when(service.createSlogan(any(SloganDto.class))).thenReturn(null);

		// Act
		ResponseEntity<SloganDto> response = controller.createSlogan(getSloganDto());

		// Assert
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertNull(response.getBody());
	}

	private SloganDto getSloganDto() {
		SloganDto slogan = new SloganDto();
		slogan.setId(1L);
		slogan.setUserId(1L);
		slogan.setDescription("description");
		return slogan;
	}
}
