package com.wolfhack.mapper;

import com.wolfhack.model.domain.User;
import com.wolfhack.model.entity.UserEntity;
import com.wolfhack.model.dto.UserRegisterDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	UserEntity toEntity(User user);

	User toModel(UserEntity userEntity);

	User toModel(UserRegisterDto userRegisterDto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(User from, @MappingTarget UserEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User partialUpdate(User from, @MappingTarget User to);
}