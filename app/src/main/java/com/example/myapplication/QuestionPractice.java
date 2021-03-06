package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
        deck = intent.getStringExtra(MainActivity.CHOSEN_DECK);

        Button backButtonQuestionPractice = (Button)findViewById(R.id.BackButton);

        backButtonQuestionPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPractice.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        Button solutionButton = (Button)findViewById(R.id.SolutionButton);
//
//        solutionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(QuestionPractice.this, Solution.class);
//                startActivity(intent);
//            }
//        });

//        Button editQuestionButton = (Button)findViewById(R.id.EditQuestionButton);
//
//        editQuestionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(QuestionPractice.this, EditQuestion.class);
//                startActivity(intent);
//            }
//        });
        Button retryButton = (Button)findViewById(R.id.RetryButton);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryQuestion();
//                Intent intent = new Intent(QuestionPractice.this, QuestionPractice.class);
//                startActivity(intent);
            }
        });

        Button newQuestionPractice = (Button)findViewById(R.id.NewQuestionButton);

        newQuestionPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion();
            }
        });

        final QuestionDatabase db = QuestionDatabase.getInstance(this);
        questions = db.questionDao().getQuestionsForDeck(deck);
        askQuestion();
        //
    }

    /**
     * asks a new question to the user 
     */
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
        resetCheckButton();
        TextView accuracyText = findViewById(R.id.accuracyText);
        accuracyText.setText("Answer to " + question.getAnswerPrecision() + " precision ");
    }

    /**
     * randomizes the numbers of the current question
     */
    public void retryQuestion(){
        question.initialize();
        Log.d("QuestionTest",question.getVariables().toString());
        Log.d("QuestionTest",question.getVars().toString());
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(question.getQuestion());
        EditText answerText = findViewById(R.id.answerText);
        answerText.setText("");
        resetCheckButton();
    }

    /**
     * resets the check button if it was red or green
     */
    public void resetCheckButton(){
        Button checkAnswerButton = findViewById(R.id.CheckButton);
        checkAnswerButton.setTextColor(Color.BLACK);
        checkAnswerButton.setText("Check");
    }

    /**
     * checks if the user's answer is correct
     * @return true if the user's answer is correct, false otherwise
     */
    public boolean checkAnswer(){
        EditText answerText = findViewById(R.id.answerText);
        double answer;
        try {
            answer = Double.parseDouble(answerText.getText().toString());
        }
        catch (Exception e){
            answer = Double.NaN;
        }
        return question.checkAnswer(answer);
    }

    /**
     * Checks answer and tells user if they are correct or wrong
     * @param v button which checks answer
     */
    public void PerformCheckAnswer(View v){
        boolean correct = checkAnswer();
        Button answer = findViewById(R.id.CheckButton);
        TextView answerText = findViewById(R.id.answerText);
        if (answerText.getText().toString().isEmpty());
        else if (correct) {
            answer.setTextColor(Color.GREEN);
        }
        else if (!correct) {
            answer.setTextColor(Color.RED);
        }

    }

}

