package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.request.UpdateUserDTO;
import com.technophiles.diaryapp.controllers.response.DeleteUserResponse;
import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.exceptions.UserNotFoundException;
import com.technophiles.diaryapp.mapper.UserMapper;
import com.technophiles.diaryapp.mapper.UserMapperImpl;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.DiaryRepositories;
import com.technophiles.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper = new UserMapperImpl();

    @Autowired
    DiaryRepositories diaryRepositories;
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

    @Override
    public String updateId(String id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
         boolean isUpdated = false;

         if(!(updateUserDTO.getEmail() == null || updateUserDTO.getEmail().trim().equals(""))){
             user.setEmail(updateUserDTO.getEmail());
             isUpdated = true;
         }

         if(!(updateUserDTO.getPassword() == null || updateUserDTO.getPassword().trim().equals(""))){
             user.setPassword(updateUserDTO.getPassword());
             isUpdated = true;
         }
         if(isUpdated){

         userRepository.save(user);
         }

        return "user details updated successfully";
    }

    @Override
    public User findUserByIdInternal(String userId) {
        return userRepository.findByEmail(userId)
                .orElseThrow(()-> new
                        DiaryApplicationException("user does not exist"));
    }

    @Override
    public Diary addNewDiary(String userId, Diary diary) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> {throw new IllegalStateException("not found");});
        Diary savedDiary = diaryRepositories.save(diary);
        user.getDiaries().add(savedDiary);
        userRepository.save(user);
        return savedDiary;

    }


}
