package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.FindUserResponse;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.exceptions.UserNotFoundException;
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
            throw new DiaryApplicationException("user is already present");
        }
        User user = new User();
        user.setEmail(accountRequestDto.getEmail());
        user.setPassword(accountRequestDto.getPassword());

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public FindUserResponse findUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        FindUserResponse response = new FindUserResponse();
            if(optionalUser.isPresent()) {
                response.setMessage("user found");
            }else{
               throw new UserNotFoundException("user not found");
            }
        return response;
    }

//    @Override
//    public CreateAccountResponse findUserById(String id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        CreateAccountResponse response = new CreateAccountResponse();
//
//        return null;
//    }
}
