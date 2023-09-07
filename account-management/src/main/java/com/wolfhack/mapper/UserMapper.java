package com.wolfhack.mapper;

import com.wolfhack.model.domain.User;
import com.wolfhack.model.dto.UserLoginDTO;
import com.wolfhack.model.dto.UserRegisterDTO;
import com.wolfhack.model.entity.UserEntity;
import com.wolfhack.model.dto.UserProfileEditDTO;
import com.wolfhack.model.entity.UserLoginResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	UserEntity toEntity(User user);

	User toModel(UserEntity userEntity);

	User toModel(UserRegisterDTO userRegisterDto);

	User toModel(UserProfileEditDTO userProfileEditDTO);

	User toModel(UserLoginDTO userLoginDTO);

	UserLoginResponseDTO toLoginResponse(User user);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(com.wolfhack.model.entity.UserLoginResponseDTO userLoginResponseDTO, @MappingTarget UserEntity userEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(User from, @MappingTarget UserEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	UserEntity update(User from, @MappingTarget UserEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User partialUpdate(User from, @MappingTarget User to);
}