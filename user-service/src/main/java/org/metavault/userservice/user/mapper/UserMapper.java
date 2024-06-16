package org.metavault.userservice.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.metavault.userservice.role.mapper.RoleMapper;
import org.metavault.userservice.user.model.dto.UserDto;
import org.metavault.userservice.user.model.entity.User;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // User -> UserDto
    @Mapping(source = "role", target = "role")
    UserDto UserToUserDto(User user);

    // UserDto -> User
    @Mapping(source = "role", target = "role")
    User UserDtoToUser(UserDto userDto);
}
