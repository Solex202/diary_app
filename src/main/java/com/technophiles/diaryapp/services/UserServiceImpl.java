package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.CreateAccountResponse;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
//
//    public UserServiceImpl(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

    @Override
    public String createAccount(CreateAccountRequest accountRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(accountRequestDto.getEmail());
        if(optionalUser.isPresent()){
            throw new DiaryApplicationException("user is aalready present");
        }
        User user = new User();
        user.setEmail(accountRequestDto.getEmail());
        user.setPassword(accountRequestDto.getPassword());

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public CreateAccountResponse findById(String id2) {
        return null;
    }
}
