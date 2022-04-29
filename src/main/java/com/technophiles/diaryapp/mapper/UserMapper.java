package com.technophiles.diaryapp.mapper;

import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDTOToUser(UserDto userDTO);
    UserDto userToUserDTO(User user);
}
