package com.technophiles.diaryapp.services;


import com.technophiles.diaryapp.models.Diary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DiaryServiceTest {
    @Autowired
    private DiaryService diaryService;

    @Test
    void testThatCanCreateANewDiary(){
        String title = new String("new diary title");
        String id = new String("");
       Diary diary =  diaryService.createDiary(title, id);
       assertThat(diary.getTitle()).isEqualTo("new diary title");
       assertThat(diary.getOwner().getId()).isEqualTo("");
    }


}
