package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import androidx.appcompat.app.AppCompatActivity;

        import android.widget.Button;
        import android.content.Intent;
        import android.view.View;

public class NewQuestion extends AppCompatActivity {
    public Button backButtonNewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        Button backButtonNewQuestion = (Button)findViewById(R.id.backButton);

        backButtonNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewQuestion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
