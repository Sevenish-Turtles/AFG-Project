package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EditQuestion extends AppCompatActivity {
    public Button saveButtonEditQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        Button saveButtonEditQuestion = (Button) findViewById(R.id.saveButton);

        saveButtonEditQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditQuestion.this, EditTemplate.class);
                startActivity(intent);
            }
        });
    }
    public void showAlertDialog(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Alert");
            alert.setMessage("Do you want to Delete this Question?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    Intent intent = new Intent(EditQuestion.this, EditTemplate.class);
                    startActivity(intent);
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    Intent intent = new Intent(EditQuestion.this, EditQuestion.class);
                    startActivity(intent);
                }
            });
            alert.create().show();
    }
}

