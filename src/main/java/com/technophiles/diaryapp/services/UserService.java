package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.CreateAccountResponse;


public interface UserService {

    String createAccount(CreateAccountRequest accountRequestDto);

    CreateAccountResponse findById(String id2);
}
