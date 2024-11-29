package com.example.nombre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    private ListView rankingListView;
    private ArrayList<String> rankingList;
    private Button playAgainButton;
    private Button changeUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // Initialize views
        rankingListView = findViewById(R.id.rankingListView);
        playAgainButton = findViewById(R.id.btn_play_again);
        changeUserButton = findViewById(R.id.btn_change_user);
        rankingList = new ArrayList<>();

        // Load the ranking data from SharedPreferences
        loadRanking();

        // Display the ranking in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rankingList);
        rankingListView.setAdapter(adapter);

        // Set up the play again button
        playAgainButton.setOnClickListener(v -> playAgain());

        // Set up the change user button
        changeUserButton.setOnClickListener(v -> changeUser());
    }

    private void loadRanking() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Get all entries in SharedPreferences
        Map<String, ?> allEntries = prefs.getAll();

        ArrayList<PlayerRecord> playerRecords = new ArrayList<>();

        // Filter entries to find scores by username
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_highestScore")) {
                String username = entry.getKey().replace("_highestScore", "");
                int score = (int) entry.getValue();
                playerRecords.add(new PlayerRecord(username, score));
            }
        }

        // Sort the list by score in descending order
        Collections.sort(playerRecords, Comparator.comparingInt(PlayerRecord::getScore).reversed());

        // Populate the rankingList with sorted records
        for (PlayerRecord record : playerRecords) {
            rankingList.add(record.getUsername() + ": " + record.getScore());
        }
    }

    // Method to handle playing again
    private void playAgain() {
        // Get the current username from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String currentUsername = prefs.getString("username", "");

        // Start GameActivity with the current username
        Intent intent = new Intent(RankingActivity.this, GameActivity.class);
        intent.putExtra("username", currentUsername);
        startActivity(intent);
    }

    // Method to handle changing the user
    private void changeUser() {
        // Go back to MainActivity to choose a new username
        Intent intent = new Intent(RankingActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Simple class to store player records
    private static class PlayerRecord {
        private final String username;
        private final int score;

        public PlayerRecord(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public int getScore() {
            return score;
        }
    }
}
