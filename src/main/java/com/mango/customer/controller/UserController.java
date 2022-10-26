package com.mango.customer.controller;

import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.model.dto.UserDto;
import com.mango.customer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;

	@Operation(summary = "Creates a user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Creates a user",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = UserDto.class))}),
	})
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return ResponseEntity.ok().body(userService.createUser(userDto));
	}

	@Operation(summary = "Update a user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Update a user",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = SloganDto.class))}),
		@ApiResponse(responseCode = "404", description = "User not found",
			content = @Content)
	})
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {
		UserDto response = userService.updateUser(userDto, userId);
		if(response == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(response);
	}
}
