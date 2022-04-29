package com.technophiles.diaryapp.models;


import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter@Getter@Document@NoArgsConstructor
public class User {
    @Id
    private String id;
    @NotNull
    private  String email;
    @NotNull @NotBlank
    private  String password;
    private Set<Diary> diaries;


    @Override
    public String toString() {
        return String.format("UserId: %s" + "Email: %s" , id, email );
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        diaries = new HashSet<>();
    }
}
