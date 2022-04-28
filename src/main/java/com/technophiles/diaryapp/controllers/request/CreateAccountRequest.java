package com.technophiles.diaryapp.controllers.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String email;
    private String password;
}
