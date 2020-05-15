package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionPractice extends AppCompatActivity {
    public Button backButtonQuestionPractice;
    public Button solutionButton;
    public Button editQuestionButton;
    public Button retryButton;
    public Button newQuestionPractice;
    public String deck;
    public List<QuestionData> questions;
    public Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_practice);

        Intent intent = getIntent();
        intent.getStringExtra(MainActivity.CHOSEN_DECK);

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

        final QuestionDatabase db = QuestionDatabase.getInstance(this);
        setDeck("Geometry");
        questions = db.questionDao().getQuestionsForDeck(deck);
        askQuestion();
    }

    public void newQuestion(View v){
        askQuestion();
    }

    public void askQuestion() {
        Random random = new Random();
        Log.d("QuestionTest",questions.size()+"");
        QuestionData data = questions.get(random.nextInt(questions.size()));
        question = new Question(data);
        Log.d("QuestionTest",question.getVariables().toString());
        Log.d("QuestionTest",question.getVars().toString());
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(question.getQuestion());
        EditText answerText = findViewById(R.id.answerText);
        answerText.setText("");
    }

    public void setDeck(String deck){
        this.deck = deck;
    }

    public boolean checkAnswer(){
        EditText answerText = findViewById(R.id.answerText);
        double answer;
        try {
            answer = Double.parseDouble(answerText.getText().toString());
        }
        catch (Exception e){
            answer = 0;
        }
        return question.checkAnswer(answer);
    }

    public void PerformCheckAnswer(View v){
        boolean correct = checkAnswer();
        if (correct) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT);
            Log.d("QuestionTest","Correct");
        }
        if (!correct) {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT);
            Log.d("QuestionTest","Incorrect");
        }
    }

}
