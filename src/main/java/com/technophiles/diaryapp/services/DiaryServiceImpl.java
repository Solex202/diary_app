package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.exceptions.DiaryApplicationException;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.DiaryRepositories;
import com.technophiles.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepositories diaryRepositories;

    @Override
    public Diary createDiary(String title, String id) {
        User user = userRepository
                .findById(id).orElseThrow(()-> new DiaryApplicationException("user not found"));

        Diary diary = new Diary(title,user);
        return diaryRepositories.save(diary);
    }
}
