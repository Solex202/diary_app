package com.technophiles.diaryapp.controllers.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDTO {

    private String email;
    private String password;
}
