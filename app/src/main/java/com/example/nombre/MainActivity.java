package com.example.nombre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button saveUsernameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);  // Make sure this ID matches the one in XML
        saveUsernameButton = findViewById(R.id.btn_save_username); // Make sure this ID matches the one in XML

        // Set onClickListener for save button
        saveUsernameButton.setOnClickListener(v -> saveUsername());
    }

    private void saveUsername() {
        String username = usernameEditText.getText().toString();

        // Check if username is not empty
        if (username.isEmpty()) {
            usernameEditText.setError("Username cannot be empty");
            return;
        }

        // Save username in SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username); // Save username
        editor.apply(); // Commit changes

        // After saving, start the game activity and pass the username
        startGameActivity(username);
    }

    private void startGameActivity(String username) {
        // Pass the username to the game activity via Intent
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("username", username);  // Pass username to GameActivity
        startActivity(intent);
    }
}
