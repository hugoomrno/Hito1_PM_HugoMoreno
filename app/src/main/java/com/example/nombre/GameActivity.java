package com.example.nombre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView levelTextView;
    private Button tapButton;
    private Button finishGameButton;
    private int score;
    private int level;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the username from the intent
        username = getIntent().getStringExtra("username");

        // Initialize views
        scoreTextView = findViewById(R.id.scoreTextView);
        levelTextView = findViewById(R.id.levelTextView);
        tapButton = findViewById(R.id.tapButton);
        finishGameButton = findViewById(R.id.finishGameButton);

        // Initialize score and level
        score = 0;
        level = 1;

        // Set the initial text for score and level
        scoreTextView.setText("Score: " + score);
        levelTextView.setText("Level: " + level);

        // Set the tap button listener
        tapButton.setOnClickListener(v -> {
            // Increment score when the button is tapped
            incrementScore();
        });

        // Set the finish game button listener
        finishGameButton.setOnClickListener(v -> {
            // Save the score as a record if it's the highest
            saveRecordIfHighest();

            // When the game is finished, go to the ranking activity
            Intent intent = new Intent(GameActivity.this, RankingActivity.class);
            startActivity(intent);
        });
    }

    private void incrementScore() {
        score++;
        // Update the score
        scoreTextView.setText("Score: " + score);

        // Check if the player has reached the threshold for the next level
        if (score >= level * 10) {  // For example, level 1 requires 10 taps, level 2 requires 20 taps, etc.
            level++;
            levelTextView.setText("Level: " + level);
        }
    }

    private void saveRecordIfHighest() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int highestScore = prefs.getInt(username + "_highestScore", 0);

        // Save the score if it's higher than the previous highest score
        if (score > highestScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(username + "_highestScore", score);
            editor.apply();
        }
    }
}
