package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final QuestionDatabase db = QuestionDatabase.getInstance(this);
        String questionString = "What is the length of the hypotenuse if the legs are :x: and :y:?";
        String vars = "x,1,10,1 ; y,1,10,1";
        String exps = "hypot,Math.sqrt(x*x+y*y)";
        String answer = "hypot";
        double precision = .01;

        QuestionDeckData deck1 = new QuestionDeckData("Geometry");
        QuestionDeckData deck2 = new QuestionDeckData("Physics");

        QuestionData q = new QuestionData( deck1.getQuestionDeckName(),"Hypotenuse",questionString,vars,exps,answer,precision);
        QuestionData q2 = new QuestionData(deck2.getQuestionDeckName(),"ramp","What is the normal force of a block of mass :m: on a plane inclined at :deg: degrees?",
                "deg,15,70,1 ; m,1,50,1", "rads,deg*Math.PI/180 ; FN,(m*9.8)*Math.cos(rads)","FN",.01);

        db.questionDao().clearTable();
        db.deckDao().clearTable();

        db.deckDao().insert(deck1,deck2);
        db.questionDao().insertAll(q,q2);

        Log.d("Question test", "question size: " + db.questionDao().getAllQuestions().size());
        Log.d("Question test", "deck size: " + db.deckDao().getAllDecks().size());
        Log.d("Question test", "hypot deck size: " + db.questionDao().getQuestionsForDeck(deck1.getQuestionDeckName()).size());

        Log.d("Question test","Hypot Question: "+db.questionDao().getQuestionsForDeck(deck2.getQuestionDeckName())
        .get(0).getQuestion());

        List<QuestionData> questions = db.questionDao().getQuestionsForDeck(deck1.getQuestionDeckName());
        Question question1 = new Question(questions.get(0));
        Log.d("Question test",question1.getQuestion());
        Log.d("Question test",""+question1.getAnswer());
        questions = db.questionDao().getQuestionsForDeck(deck2.getQuestionDeckName());
        question1 = new Question(questions.get(0));
        Log.d("Question test",question1.getQuestion());
        Log.d("Question test",""+question1.getAnswer());
    }


}
