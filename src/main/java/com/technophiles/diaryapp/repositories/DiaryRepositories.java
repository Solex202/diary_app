package com.technophiles.diaryapp.repositories;

import com.technophiles.diaryapp.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepositories  extends MongoRepository<Diary, String> {
}
