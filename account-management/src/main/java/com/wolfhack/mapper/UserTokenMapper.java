package com.wolfhack.mapper;

import com.wolfhack.model.domain.UserToken;
import com.wolfhack.model.dto.UserTokenResponseDTO;
import com.wolfhack.model.entity.UserTokenEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserTokenMapper {
	UserTokenEntity toEntity(UserToken userToken);

	UserToken toModel(UserTokenEntity userTokenEntity);

	UserTokenResponseDTO toResponse(UserToken userToken);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserTokenEntity partialUpdate(UserToken from, @MappingTarget UserTokenEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	UserTokenEntity update(UserToken from, @MappingTarget UserTokenEntity to);
}