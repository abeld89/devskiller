package com.mango.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 30)
	private String name;

	@NotBlank
	@Size(min = 3, max = 50)
	private String lastName;

	@NotBlank
	@Size(min = 5, max = 150)
	private String Address;

	@NotBlank
	@Size(min = 3, max = 30)
	private String city;

	@NotBlank
	@Size(min = 5, max = 150)
	private String email;
}
