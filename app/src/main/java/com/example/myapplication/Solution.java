package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Solution extends AppCompatActivity {

    public Button backButtonSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        Button backButtonSolution = (Button) findViewById(R.id.BackButton2);

        backButtonSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Solution.this, QuestionPractice.class);
                startActivity(intent);
            }
        });
    }
}
