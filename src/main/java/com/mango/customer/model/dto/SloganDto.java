package com.mango.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SloganDto {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 255)
	private String description;

	@NotEmpty
	private Long userId;

}
