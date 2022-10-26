package com.mango.customer.controller;

import com.mango.customer.handler.exceptions.SloganLimitException;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.service.SloganService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/campaigns/slogan")
@AllArgsConstructor
@Slf4j
public class SloganController {

	private final SloganService sloganService;

	@Operation(summary = "Creates a slogan")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Creates a slogan",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = SloganDto.class))}),
		@ApiResponse(responseCode = "204", description = "Limit of slogans by user",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = SloganLimitException.class))}),
		@ApiResponse(responseCode = "404", description = "User not found",
			content = @Content)
	})
	@PostMapping
	public ResponseEntity<SloganDto> createSlogan(@Valid @RequestBody SloganDto sloganDto) {
		SloganDto slogan = sloganService.createSlogan(sloganDto);
		if (slogan == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(slogan);
	}
}
