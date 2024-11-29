package com.example.nombre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectionActivity extends AppCompatActivity {

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        // Obtener el nombre del jugador desde la actividad anterior
        userName = getIntent().getStringExtra("USER_NAME");

        // Botón para iniciar el primer nivel
        Button level1Button = findViewById(R.id.btn_level1);
        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(1); // Nivel 1
            }
        });

        // Botón para iniciar el segundo nivel
        Button level2Button = findViewById(R.id.btn_level2);
        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(2); // Nivel 2
            }
        });
    }

    private void startGame(int level) {
        // Pasar el nombre del jugador y el nivel al juego
        Intent intent = new Intent(LevelSelectionActivity.this, GameActivity.class);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("LEVEL", level);
        startActivity(intent);
    }
}
