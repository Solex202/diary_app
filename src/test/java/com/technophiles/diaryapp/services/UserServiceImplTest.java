package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.DeleteUserResponse;
import com.technophiles.diaryapp.controllers.response.UserDto;
import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataMongoTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;



    @Test
    void testCreateAccount(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("lotA@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);
        assertThat(id).isNotNull();
    }

    @Test
    void testThatUserCanBeFound(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("deoala@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 =  CreateAccountRequest.builder()
                .email("ngozi@gmail.com")
                .password("ngiri")
                .build();

        String id2 = userService.createAccount(accountRequest2);

        UserDto accountResponse = userService.findUserById(id2);

        assertThat(accountResponse.getMessage(), is("user found"));
    }

    @Test
    public void testThatAllUsersCanBeFound(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("leo@gmail.com")
                .password("password")
                .build();

         userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 =  CreateAccountRequest.builder()
                .email("mmeso@gmail.com")
                .password("ngiri")
                .build();

         userService.createAccount(accountRequest2);

        assertThat(userService.findAllUser().size(), is(2));
    }

    @Test
    public void testThatUserCanBeDeleted(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("deoala@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 =  CreateAccountRequest.builder()
                .email("ngozi@gmail.com")
                .password("ngiri")
                .build();

        String id2 = userService.createAccount(accountRequest2);

        assertThat(userService.findAllUser().size(), is(2));

        DeleteUserResponse deleteResponse = userService.deleteUser(id);
        assertThat(userService.findAllUser().size(), is(1));
        assertThat(deleteResponse.getMessage(),is("user deleted"));

    }

    @Test
    public void testThatUserprofileCanBeUpdated(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("deoala@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 =  CreateAccountRequest.builder()
                .email("ngozi@gmail.com")
                .password("ngiri")
                .build();

        String id2 = userService.createAccount(accountRequest2);

        assertThat(userService.findAllUser().size(), is(2));

//        UpdateProfileRequest
    }

    @Test
    @DisplayName("when you try to create an email that already exist in db" +
    "The create account service should throw exception")
    void thatTestThrowsDiaryExceptionWhenEmailAlreadyExist(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("deoala@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 = CreateAccountRequest.builder()
                .email("deoala@gmail.com")
                .password("password")
                .build();


        assertThatThrownBy(()->userService.createAccount(accountRequest2))
                .isInstanceOf(DiaryApplicationException.class).hasMessage("user is already present");

//        String id = userService.createAccount(accountRequest);


    }
    @Test
    void testThatCanGetUserInformation(){

    }

    @AfterEach
    void tearDown(){
        repo.deleteAll();
    }

}