package com.technophiles.diaryapp.controllers;


import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.APIResponse;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createNewUserAccount(@RequestBody CreateAccountRequest accountRequest){

        try{
            APIResponse response = APIResponse.builder()
                    .message("id:" + userService.createAccount(accountRequest))
                    .isSuccessful(true)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch(DiaryApplicationException ex){
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }



    }
}
