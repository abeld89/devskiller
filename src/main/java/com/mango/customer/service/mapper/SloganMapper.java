package com.mango.customer.service.mapper;

import com.mango.customer.model.Slogan;
import com.mango.customer.model.dto.SloganDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SloganMapper {

	//@Mapping(ignore = true, target = "id")
	Slogan toEntity(SloganDto dto);

	//@Mapping(source = "user.id", target = "userId")
	SloganDto toDto(Slogan entity);
}
