package com.asd.a31c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView scoreText;
    private Button restartButton, finishButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        restartButton = findViewById(R.id.restartButton);
        finishButton = findViewById(R.id.finishButton);
        preferences = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);

        int score = getIntent().getIntExtra("score", 0);
        scoreText.setText("Your Score: " + score);

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> finishAffinity());
    }
}
