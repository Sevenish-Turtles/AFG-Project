package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class QuestionPractice extends AppCompatActivity {
    public Button backButtonQuestionPractice;
    public Button solutionButton;
    public Button editQuestionButton;

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
    }

}
