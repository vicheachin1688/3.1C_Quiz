package com.asd.a31c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private RadioButton optionA, optionB, optionC;
    private Button submitButton;
    private ProgressBar progressBar;

    private int currentIndex = 0;
    private int score = 0;

    private String[] questions = {
            "What is the result of 7 × 6?",
            "2 + 2 = ?",
            "Java is a ...?"
    };

    private String[][] options = {
            {"40", " 36", "42"},
            {"3", "4", "5"},
            {"Snake", "Programming Language", "Drink"}
    };

    private int[] answers = {2, 1, 1}; // correct option indices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);

        // Load the first question
        loadQuestion();

        // Handle submit
        submitButton.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestion() {
        // Set question and options
        questionText.setText(questions[currentIndex]);
        optionA.setText(options[currentIndex][0]);
        optionB.setText(options[currentIndex][1]);
        optionC.setText(options[currentIndex][2]);

        // Set tags for answer checking
        optionA.setTag("0");
        optionB.setTag("1");
        optionC.setTag("2");

        // Reset selection and colors
        optionsGroup.clearCheck();
        optionA.setBackgroundColor(Color.TRANSPARENT);
        optionB.setBackgroundColor(Color.TRANSPARENT);
        optionC.setBackgroundColor(Color.TRANSPARENT);

        // Update progress bar
        progressBar.setProgress((int) (((double) currentIndex / questions.length) * 100));
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId == -1) return; // No option selected

        RadioButton selectedRadio = findViewById(selectedId);
        int selectedOption = Integer.parseInt(selectedRadio.getTag().toString());

        if (selectedOption == answers[currentIndex]) {
            selectedRadio.setBackgroundColor(Color.GREEN);
            score++;
        } else {
            selectedRadio.setBackgroundColor(Color.RED);
            RadioButton correctRadio = (RadioButton) optionsGroup.getChildAt(answers[currentIndex]);
            correctRadio.setBackgroundColor(Color.GREEN);
        }

        // Wait 1 second before going to next question or results
        new Handler().postDelayed(() -> {
            currentIndex++;
            if (currentIndex < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
