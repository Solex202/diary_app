package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;


public interface UserService {

    String createAccount(CreateAccountRequest accountRequestDto);
}
