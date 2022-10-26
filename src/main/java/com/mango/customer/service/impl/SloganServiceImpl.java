package com.mango.customer.service.impl;

import com.mango.customer.handler.exceptions.SloganLimitException;
import com.mango.customer.model.Slogan;
import com.mango.customer.model.User;
import com.mango.customer.model.dto.SloganDto;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.SloganService;
import com.mango.customer.service.mapper.SloganMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SloganServiceImpl implements SloganService {

	private final SloganRepository sloganRepository;
	private final UserRepository userRepository;

	private final SloganMapper sloganMapper;


	@Override
	public SloganDto createSlogan(SloganDto sloganDto) {

		Optional<User> user = userRepository.findById(sloganDto.getId());
		if (user.isPresent()) {
			Long slogansByUser = sloganRepository.countByUserId(user.get().getId());
			if (slogansByUser < 3) {
				Slogan sloganToSave = sloganMapper.toEntity(sloganDto);
				sloganToSave.setUser(user.get());
				return sloganMapper.toDto(sloganRepository.save(sloganToSave));
			} else {
				throw new SloganLimitException();
			}
		}

		return null;
	}
}
