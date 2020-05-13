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
                startActivity(intent);
            }
        });

        Button newButton = (Button) findViewById(R.id.NewButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTemplate.class);
                startActivity(intent);
            }
        });


        Button editButton = (Button) findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditTemplate.class);
                startActivity(intent);
            }
        });
        test();
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

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ButtonClicked();
                    }
                });

                row.addView(button);
                buttonList.addView(row);

            }
        }

    private void ButtonClicked() {
        Toast.makeText(this,"Button clicked",Toast.LENGTH_SHORT).show();
    }

    public void test(){
        QuestionDatabase db = QuestionDatabase.getInstance(this);
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
    }

    }

