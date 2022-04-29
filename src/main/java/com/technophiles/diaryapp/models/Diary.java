package com.technophiles.diaryapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@NoArgsConstructor
@Getter
@Setter

public class Diary {
    @Id
    private String id;
    private String title;
    private LocalDateTime creationTime;
    @DBRef
    private User owner;

    public Diary(String title,  User owner) {
        this.title = title;
//        this.creationTime = creationTime;
        this.owner = owner;
    }




}
