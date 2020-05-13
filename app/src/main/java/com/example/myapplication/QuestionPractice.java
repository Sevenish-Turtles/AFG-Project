package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.content.Intent;
import android.view.View;

import java.util.List;
import java.util.Random;


public class QuestionPractice extends AppCompatActivity {
    public Button backButtonQuestionPractice;
    public Button solutionButton;
    public Button editQuestionButton;
    public Button retryButton;
    public Button newQuestionPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_practice);

        Button backButtonQuestionPractice = (Button)findViewById(R.id.BackButton);

        backButtonQuestionPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button solutionButton = (Button)findViewById(R.id.SolutionButton);

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, Solution.class);
                startActivity(intent);
            }
        });

        Button editQuestionButton = (Button)findViewById(R.id.EditQuestionButton);

        editQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, EditQuestion.class);
                startActivity(intent);
            }
        });
        Button retryButton = (Button)findViewById(R.id.RetryButton);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, QuestionPractice.class);
                startActivity(intent);
            }
        });

        Button newQuestionPractice = (Button)findViewById(R.id.NewQuestionButton);

        newQuestionPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, QuestionPractice.class);
                startActivity(intent);
            }
        });

        List<QuestionData> questions = db.questionDao().getQuestionsForDeck("Geometry");
        Random random = new Random();
        QuestionData data = questions.get(random.nextInt(questions.size()));
        Question q5 = new Question(data);
        d5.getQuestion();
        d5.checkAnswer(0.5);

        String correct = new String ("Correct!");
        String incorrect = new String ("Incorrect");
        if (q5.checkAnswer(ans)){
            System.out.pritnln(correct);
        }
        else {
            System.out.println(incorrect);
        }
    }

}
