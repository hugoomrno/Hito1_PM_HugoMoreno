package com.example.nombre;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EnterUsernameActivity extends AppCompatActivity {

    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_username);

        usernameEditText = findViewById(R.id.usernameEditText);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();

                if (!username.isEmpty()) {
                    // Guardamos el nombre de usuario en las SharedPreferences
                    getSharedPreferences("UserPrefs", MODE_PRIVATE).edit().putString("username", username).apply();

                    // Redirigimos a la actividad principal del juego
                    Intent intent = new Intent(EnterUsernameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Si el nombre de usuario está vacío, mostramos un mensaje
                    Toast.makeText(EnterUsernameActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
