package org.example.backend.mappers;

import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.response_DTOs.UserResponseDto;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toModel(UserDto userDto);

    @Mapping(target = "fullName", source = ".", qualifiedByName = "mapFullName")
    UserResponseDto toDto(User user);

    void updateUserFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);

    @Named("mapFullName")
    default String mapFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
