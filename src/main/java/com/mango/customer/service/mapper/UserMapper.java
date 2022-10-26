package com.mango.customer.service.mapper;

import com.mango.customer.model.User;
import com.mango.customer.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(ignore = true, target = "id")
	User toEntity(UserDto dto);

	UserDto toDto(User entity);
}
