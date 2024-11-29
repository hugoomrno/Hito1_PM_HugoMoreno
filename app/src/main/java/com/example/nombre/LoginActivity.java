package com.example.nombre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameEditText = findViewById(R.id.nameEditText);

        // Botón para iniciar el juego
        findViewById(R.id.btn_start_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();
                if (userName.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    // Pasar el nombre del jugador a la siguiente actividad
                    Intent intent = new Intent(LoginActivity.this, LevelSelectionActivity.class);
                    intent.putExtra("USER_NAME", userName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
