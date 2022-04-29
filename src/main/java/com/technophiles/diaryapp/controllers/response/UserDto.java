package com.technophiles.diaryapp.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor@AllArgsConstructor
public class UserDto {
    private String id;
    private String email;

    private String message;
}
