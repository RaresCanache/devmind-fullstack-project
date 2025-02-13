package org.example.backend.mappers;

import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toModel(UserDto userDto);

    UserDto toDto(User user);

    void updateUserFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
