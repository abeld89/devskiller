package com.mango.customer.service.mapper;

import com.mango.customer.model.Slogan;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserRepository.class})
public interface SloganMapper {

	@Mappings({
		@Mapping(source = "userId", target = "user"),
		@Mapping(source = "id", target = "id")
	})
	Slogan toEntity(SloganDto dto);

	@Mapping(source = "user.id", target = "userId")
	SloganDto toDto(Slogan entity);
}
