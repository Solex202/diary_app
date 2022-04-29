package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.DeleteUserResponse;
import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.models.User;

import java.util.List;


public interface UserService {

    String createAccount(CreateAccountRequest accountRequestDto);

    UserDto findUserById(String id);

    List<User> findAllUser();

    DeleteUserResponse deleteUser(String id);

//    DeleteUserResponse deleteUser(id);


//    CreateAccountResponse findUserById(String id);
}
