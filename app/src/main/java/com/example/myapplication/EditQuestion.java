package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class EditQuestion extends AppCompatActivity {

    public Button backButtonEditQuestion;
    public Button doneButtonEditQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);
            Button backButtonEditQuestion = (Button) findViewById(R.id.backButton3);

            backButtonEditQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditQuestion.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        Button doneButtonEditQuestion = (Button) findViewById(R.id.doneButton);

        doneButtonEditQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditQuestion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

