package com.wolfhack.mapper;

import com.wolfhack.model.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserEventMapper extends EventMapper<User> {

}
