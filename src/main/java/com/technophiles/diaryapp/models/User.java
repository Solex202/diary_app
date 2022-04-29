package com.technophiles.diaryapp.models;


import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter@Getter@Document@NoArgsConstructor
public class User {
    @Id
    private String id;
    @UniqueElements
    private  String email;
    private  String password;


    @Override
    public String toString() {
        return String.format("UserId: %s" + "Email: %s" , id, email );
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
