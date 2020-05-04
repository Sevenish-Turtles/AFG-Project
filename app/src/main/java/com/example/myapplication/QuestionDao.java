package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(QuestionData q);

    @Insert
    void insertAll(QuestionData...q);

    @Update
    void update(QuestionData q);

    @Delete
    void delete(QuestionData q);

    @Query("SELECT * FROM QuestionData")
    List<QuestionData> getAllQuestions();
}
