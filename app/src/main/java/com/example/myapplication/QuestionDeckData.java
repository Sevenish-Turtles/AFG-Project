package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionDeckData {
//    @PrimaryKey(autoGenerate = true)
//    private int id;
    @PrimaryKey
    @NonNull
    private String questionDeckName;
//    @Embedded
//    private QuestionData question;

    public QuestionDeckData(String questionDeckName) {
        this.questionDeckName = questionDeckName;
    }

    public void setQuestionDeckName(String questionDeckName) {
        this.questionDeckName = questionDeckName;
    }

//    public void setQuestion(QuestionData question) {
//        this.question = question;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }

    public String getQuestionDeckName() {
        return questionDeckName;
    }

//    public QuestionData getQuestion() {
//        return question;
//    }
}
