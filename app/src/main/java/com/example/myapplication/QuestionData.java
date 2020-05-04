package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionData {
    @PrimaryKey(autoGenerate = true)
    private int question_id;

    private String questionName;
    private String question;
    private String variables;
    private String expressions;
    private String answer;
    private double answerPrecision;

    public QuestionData(String questionName, String question, String variables, String expressions, String answer, double answerPrecision) {
        this.questionName = questionName;
        this.question = question;
        this.variables = variables;
        this.expressions = expressions;
        this.answer = answer;
        this.answerPrecision = answerPrecision;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getQuestion() {
        return question;
    }

    public String getVariables() {
        return variables;
    }

    public String getExpressions() {
        return expressions;
    }

    public String getAnswer() {
        return answer;
    }

    public double getAnswerPrecision() {
        return answerPrecision;
    }
}
