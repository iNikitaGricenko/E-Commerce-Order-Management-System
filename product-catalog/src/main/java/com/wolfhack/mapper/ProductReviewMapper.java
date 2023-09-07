package com.wolfhack.mapper;

import com.wolfhack.model.domain.ProductReview;
import com.wolfhack.model.dto.ProductReviewCreationDTO;
import com.wolfhack.model.entity.ProductReviewEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductReviewMapper {
	ProductReviewEntity toEntity(ProductReview productReview);

	ProductReview toModel(ProductReviewEntity productReviewEntity);

	ProductReview toModel(ProductReviewCreationDTO productReviewCreationDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductReviewEntity partialUpdate(ProductReview from, @MappingTarget ProductReviewEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductReviewEntity update(ProductReview from, @MappingTarget ProductReviewEntity to);

}