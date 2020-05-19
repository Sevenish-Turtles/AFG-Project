package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionDeckData {
    @PrimaryKey
    @NonNull
    private String questionDeckName;


    public QuestionDeckData(String questionDeckName) {
        this.questionDeckName = questionDeckName;
    }

    public void setQuestionDeckName(String questionDeckName) {
        this.questionDeckName = questionDeckName;
    }

    public String getQuestionDeckName() {
        return questionDeckName;
    }
}
