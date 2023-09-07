package com.wolfhack.mapper;

import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.model.entity.PasswordResetRequestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PasswordResetRequestMapper {

	PasswordResetRequestEntity toEntity(PasswordResetRequest passwordResetRequest);

	PasswordResetRequest toModel(PasswordResetRequestEntity passwordResetRequestEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PasswordResetRequestEntity partialUpdate(PasswordResetRequest from, @MappingTarget PasswordResetRequestEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PasswordResetRequestEntity update(PasswordResetRequest from, @MappingTarget PasswordResetRequestEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PasswordResetRequest partialUpdate(PasswordResetRequest from, @MappingTarget PasswordResetRequest to);


}
