package com.technophiles.diaryapp.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryApplicationException extends RuntimeException {


    public DiaryApplicationException(String message){
        super(message);
    }
}
