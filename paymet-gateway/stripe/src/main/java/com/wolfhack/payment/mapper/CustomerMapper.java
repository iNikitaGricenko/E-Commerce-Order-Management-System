package com.wolfhack.payment.mapper;

import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.entity.CustomerInformationEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
	CustomerInformationEntity toEntity(CustomerInformation customerInformation);

	CustomerInformation toModel(CustomerInformationEntity customerInformationEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	CustomerInformationEntity partialUpdate(CustomerInformation from, @MappingTarget CustomerInformationEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	CustomerInformationEntity update(CustomerInformation from, @MappingTarget CustomerInformationEntity to);
}