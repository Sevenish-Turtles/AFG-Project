package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewTemplate extends AppCompatActivity {
    public Button makeNewButtonNewTemplate;
    public Button saveNewTemplate;
    public Button practiceNewTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_template);

        Button makeNewButtonNewTemplate = (Button) findViewById(R.id.makeNewQuestion);

        makeNewButtonNewTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTemplate.this, NewQuestion.class);
                startActivity(intent);
            }
        });

        Button saveNewTemplate = (Button) findViewById(R.id.saveButton);

        saveNewTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTemplate.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button practiceNewTemplate = (Button) findViewById(R.id.practiceButton);

        practiceNewTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTemplate.this, QuestionPractice.class);
                startActivity(intent);
            }
        });
    }
    public void showAlertDialog(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert");
        alert.setMessage("Do you want to Delete this Template?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(NewTemplate.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        alert.create().show();
    }
}
