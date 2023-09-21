package com.wolfhack.mapper;

import com.wolfhack.model.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductEventMapper extends EventMapper<Product> {

}
