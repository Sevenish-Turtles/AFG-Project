package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    public Button newButton;
//    public Button editButton;
//    public Button practiceButton;
    public String chosenDeck = "";
    public static final String CHOSEN_DECK = "com.example.myapplication.example.chosenDeck";
    public Button chosenButton;

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
                if (!chosenDeck.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, QuestionPractice.class);
                    intent.putExtra(CHOSEN_DECK, chosenDeck);
                    startActivity(intent);
                }
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
                final Button button = new Button(this);
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
                        Log.d("QuestionTest",chosenDeck);
                        chosenButton = button;
                        showSelectedButton();
                    }
                });

                row.addView(button);
                buttonList.addView(row);
                //
            }
        }

        public void showSelectedButton(){
            for (View view:findViewById(R.id.buttonsTable).getTouchables()) {
                Button button = (Button) view;
                if (button == chosenButton)
                    button.setTextColor(Color.BLUE);
                else
                    button.setTextColor(Color.BLACK);
            }

        }

        private void populateDatabaseInitial(){
            QuestionDatabase db = QuestionDatabase.getInstance(this);
            db.questionDao().clearTable();
            db.deckDao().clearTable();
            addQuestionToDatabase("Geometry","Hypotenuse","What is the length of the hypotenuse if the legs are :x: and :y:?",
                    "x,1,10,1 ; y,1,10,1","hypot,Math.sqrt(x*x+y*y)","hypot",.01);
            addQuestionToDatabase("Physics","ramp","What is the normal force of a block of mass :m: on a plane inclined at :deg: degrees?",
                    "deg,15,70,1 ; m,1,50,1", "rads,deg*Math.PI/180 ; FN,(m*9.8)*Math.cos(rads)","FN",.01);
            addQuestionToDatabase("Physics", "Ball" , "How much time will it take for a ball thrown up on earth with initial velocity :v: meters per second and initial height :h: meters?",
                   "v,1,20,0.1 ; h,5,10,.1", "time,(-v-Math.sqrt(v*v+2*9.8*h))/-9.8", "time", 0.01);
            addQuestionToDatabase("Trigonometry", "Inverse Cosine" , "What is the value of the inverse cosine of the negative square root of :y:?",
                   "y,0,1,0.01", "X, (Math.acos(-1*Math.sqrt(y)))" , "X", 0.01);
            addQuestionToDatabase("Logarithms", "Dividing Logarithms" , "What is the value of the base 10 logarithm of :x: divided by the base 10 logarithm of :z:?",
            "x,1,100,0.1 ; z,1,100,0.1", "Y,(Math.log10(x))/(Math.log10(z))", "Y", 0.01);
            addQuestionToDatabase("Logarithms", "Natural Logarithms" , "What is the value of the natural log of :x: divided by the square root of the log of :z: minus 17?",
            "x,1,100,0.1 ; z,1,100,0.1", "Y, (Math.log(x))/(Math.sqrt(Math.log10(z))) - 17", "Y", 0.01);
            addQuestionToDatabase("Trigonometry", "Sine and Cosine" , "What is the value of the sin of :x: degrees minus the cosine of :z: degrees, all divided by 2",
                   "x,0,500,.1 ; z,0,500,.1", "Y,(Math.sin(x)-Math.cos(z))/2", "Y", 0.01);
            addQuestionToDatabase("Logarithms", "Natural Logs", "What is the value of the negative of the square root of the natural log of :x:?",
                   "x,0,200,.1", "y,-1*(Math.sqrt(Math.log(x)))", "Y", 0.01);
            addQuestionToDatabase("Physics", "Block on Ramp", "What is the height of a block going down a ramp on earth with a given velocity :v: meters per second and mass :m: kilograms?",
            "v,10,50,.01 ; m,5,10,1", "height,(v*v*0.5)/9.8", "height", 0.01);
            addQuestionToDatabase("Geometry", "Sphere Surface Area", "What is the surface area of a sphere with a radius of :r: meters?",
            "r, 1, 20, 0.1", "surface area, (4*Math.PI*r*r)", "surface area", 0.01);
        }

        private void addQuestionToDatabase(String deckName, String questionName, String questionText, String vars, String expressions, String answer, double precision){
            QuestionDatabase db = QuestionDatabase.getInstance(this);
            if (db.deckDao().getDecksWithName(deckName).size()==0)
                db.deckDao().insert(new QuestionDeckData(deckName));
            db.questionDao().insert(new QuestionData(deckName,questionName,questionText,vars,expressions,answer,precision));
        }

    }

