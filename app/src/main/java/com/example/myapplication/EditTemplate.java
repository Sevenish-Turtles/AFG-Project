package com.example.myapplication;

        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.DialogInterface;
        import android.os.Bundle;

        import android.widget.Button;
        import android.content.Intent;
        import android.view.View;

public class EditTemplate extends AppCompatActivity {
    public Button makeNewQuestion;
    public Button practiceNewQuestion;
    public Button saveNewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_template);

        Button makeNewQuestion = (Button) findViewById(R.id.MakeNewQuestion);

        makeNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTemplate.this, NewQuestion.class);
                startActivity(intent);
            }
        });

        Button practiceNewQuestion = (Button) findViewById(R.id.PracticeButton);

        practiceNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTemplate.this, QuestionPractice.class);
                startActivity(intent);
            }
        });

        Button saveNewQuestion = (Button) findViewById(R.id.Savebutton);

        saveNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTemplate.this, MainActivity.class);
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
                Intent intent = new Intent(EditTemplate.this, MainActivity.class);
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
