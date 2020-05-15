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
    void insert(QuestionDeckData...deck);

    @Update
    void update(QuestionDeckData deck);

    @Delete
    void delete(QuestionDeckData deck);

    @Query("DELETE FROM QuestionDeckData")
    void clearTable();

    @Query("SELECT * FROM QuestionDeckData")
    List<QuestionDeckData> getAllDecks();

    @Query("SELECT * FROM QUESTIONDECKDATA WHERE questionDeckName=:deckName")
    List<QuestionDeckData> getDecksWithName(String deckName);
}
