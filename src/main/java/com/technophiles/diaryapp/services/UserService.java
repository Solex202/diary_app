package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.request.UpdateUserDTO;
import com.technophiles.diaryapp.controllers.response.DeleteUserResponse;
import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;

import java.util.List;


public interface UserService {

    String createAccount(CreateAccountRequest accountRequestDto);

    UserDto findUserById(String id);

    List<User> findAllUser();

    DeleteUserResponse deleteUser(String id);

    String updateId(String id, UpdateUserDTO updateUserDTO);

    User findUserByIdInternal(String userId);

    Diary addNewDiary(String userId, Diary diary);

//    String updateId(String id2, UpdateUserDTO update);

//    DeleteUserResponse deleteUser(id);


//    CreateAccountResponse findUserById(String id);
}
