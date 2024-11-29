package com.example.nombre;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * UserProfileActivity shows user profile details like the highest score.
 */
public class UserProfileActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView highestScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Inicializar las vistas
        usernameTextView = findViewById(R.id.usernameTextView);
        highestScoreTextView = findViewById(R.id.highestScoreTextView);

        // Aquí puedes setear los valores dinámicamente, por ejemplo, con SharedPreferences
        String username = "Player1";  // Este valor puede provenir de preferencias o de una base de datos
        int highestScore = 100; // Este valor también puede venir de alguna fuente de datos

        // Mostrar el nombre de usuario y el puntaje más alto
        usernameTextView.setText("Username: " + username);
        highestScoreTextView.setText("Highest Score: " + highestScore);
    }
}
