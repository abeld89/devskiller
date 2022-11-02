package com.mango.customer.service.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.mapper.UserMapper;
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

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
public class UserControllerITTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void createUserOk() throws Exception {

		UserDto userDto = getUserDto();

		int userBeforeTest = userRepository.findAll().size();

		mvc.perform(post("/user")
				.contentType(APPLICATION_JSON)
				.content(converToDto(userDto)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(userDto.getName()))
			.andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
			.andExpect(jsonPath("$.address").value(userDto.getAddress()))
			.andExpect(jsonPath("$.city").value(userDto.getCity()))
			.andExpect(jsonPath("$.email").value(userDto.getEmail()));

		if (userRepository.findAll().size() != userBeforeTest + 1) {
			Assertions.fail("No se ha guardado el registro");
		}

	}

	@Test
	public void updateUser() throws Exception {

		List<User> users = userRepository.findAll();

		if (users.size() < 1) {
			Assertions.fail("No se puede lanzar el test porque no hay usuarios");
		}

		UserDto userDto = userMapper.toDto(users.get(0));
		userDto.setName("changeName");

		mvc.perform(put("/user/{userId}", users.get(0).getId())
				.contentType(APPLICATION_JSON)
				.content(converToDto(userDto)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(userDto.getName()))
			.andExpect(jsonPath("$.lastName").value(users.get(0).getLastName()))
			.andExpect(jsonPath("$.address").value(users.get(0).getAddress()))
			.andExpect(jsonPath("$.city").value(users.get(0).getCity()))
			.andExpect(jsonPath("$.email").value(users.get(0).getEmail()));


	}

	private String converToDto(UserDto dto) throws JsonProcessingException {
		return objectMapper.writeValueAsString(dto);
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
