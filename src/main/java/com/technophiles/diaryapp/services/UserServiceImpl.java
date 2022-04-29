package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.DeleteUserResponse;
import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.exceptions.UserNotFoundException;
import com.technophiles.diaryapp.mapper.UserMapper;
import com.technophiles.diaryapp.mapper.UserMapperImpl;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper = new UserMapperImpl();
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
    public UserDto findUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        UserDto response = new UserDto();
            if(optionalUser.isPresent()) {
                response.setMessage("user found");
            }else{
               throw new UserNotFoundException("user not found");
            }
        return response;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public DeleteUserResponse deleteUser(String id) {
        boolean optionalUser = userRepository.existsById(id);
        DeleteUserResponse response = new DeleteUserResponse();
        if(optionalUser){
            userRepository.deleteById(id);
            response.setMessage("user deleted");
        }else{
            response.setMessage("user not found");
        }
        return response;
    }




}
