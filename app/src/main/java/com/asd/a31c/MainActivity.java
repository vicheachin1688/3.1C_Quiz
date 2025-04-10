package com.asd.a31c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button startButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        startButton = findViewById(R.id.startButton);
        preferences = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);

        String savedName = preferences.getString("userName", "");
        nameInput.setText(savedName);

        startButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            preferences.edit().putString("userName", name).apply();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("userName", name);
            startActivity(intent);
        });
    }
}
