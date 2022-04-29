package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.request.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.CreateAccountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceImplTest {
    @Autowired
    private UserService userService;



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
                .email("lotA@gmail.com")
                .password("password")
                .build();

        String id = userService.createAccount(accountRequest);

        CreateAccountRequest accountRequest2 =  CreateAccountRequest.builder()
                .email("ngozi@gmail.com")
                .password("ngiri")
                .build();

        String id2 = userService.createAccount(accountRequest2);

        CreateAccountResponse accountResponse = userService.findById(id2);

        assertThat(accountResponse.getMessage(), is("user found"));



    }

}