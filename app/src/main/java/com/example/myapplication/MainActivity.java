package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button newButton;
    public Button editButton;
    public Button practiceButton;
    public String chosenDeck;
    public static final String CHOSEN_DECK = "com.example.myapplication.example.chosenDeck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final QuestionDatabase db = QuestionDatabase.getInstance(this);
        Log.d("QuestionTest",db.hashCode()+"");

        Button practiceButton = (Button) findViewById(R.id.pracButtonQues);

        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionPractice.class);
                intent.putExtra(CHOSEN_DECK,chosenDeck);
                startActivity(intent);
            }
        });

//        Button newButton = (Button) findViewById(R.id.NewButton);
//
//        newButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewTemplate.class);
//                startActivity(intent);
//            }
//        });


//        Button editButton = (Button) findViewById(R.id.editButton);
//
//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, EditTemplate.class);
//                startActivity(intent);
//            }
//        });


        populateDatabaseInitial();
        Log.d("QuestionTest",""+db.deckDao().getAllDecks());


        populateButtons();

        }

        public void populateButtons(){
            TableLayout buttonList = (TableLayout) findViewById(R.id.buttonsTable);
            final QuestionDatabase db = QuestionDatabase.getInstance(this);
            for (QuestionDeckData deck:db.deckDao().getAllDecks()){
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                button.setText(deck.getQuestionDeckName());
                final String DECK_NAME = deck.getQuestionDeckName();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chosenDeck = DECK_NAME;
                    }
                });

                row.addView(button);
                buttonList.addView(row);

            }
        }

        private void populateDatabaseInitial(){
            addQuestionToDatabase("Geometry","Hypotenuse","What is the length of the hypotenuse if the legs are :x: and :y:?",
                    "x,1,10,1 ; y,1,10,1","hypot,Math.sqrt(x*x+y*y)","hypot",.01);
            addQuestionToDatabase("Physics","ramp","What is the normal force of a block of mass :m: on a plane inclined at :deg: degrees?",
                    "deg,15,70,1 ; m,1,50,1", "rads,deg*Math.PI/180 ; FN,(m*9.8)*Math.cos(rads)","FN",.01);
        }

        private void addQuestionToDatabase(String deckName, String questionName, String questionText, String vars, String expressions, String answer, double precision){
            QuestionDatabase db = QuestionDatabase.getInstance(this);
            if (db.deckDao().getDecksWithName(deckName).size()==0)
                db.deckDao().insert(new QuestionDeckData(deckName));
            db.questionDao().insert(new QuestionData(deckName,questionName,questionText,vars,expressions,answer,precision));
        }

    }

