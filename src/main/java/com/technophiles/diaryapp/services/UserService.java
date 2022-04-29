package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.FindUserResponse;


public interface UserService {

    String createAccount(CreateAccountRequest accountRequestDto);

    FindUserResponse findUserById(String id);

//    CreateAccountResponse findUserById(String id);
}
