package com.mango.customer.service.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mango.customer.model.User;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
public class SloganControllerITTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SloganRepository sloganRepository;

	@Test
	public void createSloganOk() throws Exception {

		List<User> users = userRepository.findAll();
		if (users.size() < 1) {
			Assertions.fail("No se puede lanzar el test porque no hay usuarios");
		}

		Optional<User> userSlogan = users.stream().filter(u ->
			(sloganRepository.countByUserId(u.getId()) < 3)).findFirst();

		if (userSlogan.isEmpty()) {
			Assertions.fail("No se puede lanzar el test por falta de usuarios sin slogans.");
		}

		int slogansBeforeTest = sloganRepository.countByUserId(userSlogan.get().getId());

		SloganDto dto = new SloganDto();
		dto.setDescription("description 1");
		dto.setUserId(userSlogan.get().getId());
		mvc.perform(post("/campaigns/slogan")
				.contentType(APPLICATION_JSON)
				.content(converToDto(dto)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.description").value("description 1"))
			.andExpect(jsonPath("$.userId").value(userSlogan.get().getId().toString()));

		if (sloganRepository.countByUserId(userSlogan.get().getId()) != slogansBeforeTest + 1) {
			Assertions.fail("No se ha guardado el registro");
		}

	}

	@Test
	public void createSlogan_throws_slogan_limit_exception() throws Exception {

		List<User> users = userRepository.findAll();
		if (users.size() < 1) {
			Assertions.fail("No se puede lanzar el test porque no hay usuarios");
		}

		Optional<User> userSlogan = users.stream().filter(u ->
			(sloganRepository.countByUserId(u.getId()) >= 3)).findFirst();

		if (userSlogan.isEmpty()) {
			Assertions.fail("No se puede lanzar el test por falta de usuarios sin slogans.");
		}

		SloganDto dto = new SloganDto();
		dto.setDescription("description 1");
		dto.setUserId(userSlogan.get().getId());
		mvc.perform(post("/campaigns/slogan")
				.contentType(APPLICATION_JSON)
				.content(converToDto(dto)))
			.andExpect(status().isNotAcceptable());

	}

	private String converToDto(SloganDto dto) throws JsonProcessingException {
		return objectMapper.writeValueAsString(dto);
	}
}
