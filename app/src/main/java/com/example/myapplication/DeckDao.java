package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DeckDao {
    @Insert
    void insert(QuestionDeckData q);

    @Update
    void update(QuestionDeckData q);

    @Delete
    void delete(QuestionDeckData q);

    @Query("SELECT * FROM QuestionDeckData")
    List<QuestionDeckData> getAllQuestions();
}
