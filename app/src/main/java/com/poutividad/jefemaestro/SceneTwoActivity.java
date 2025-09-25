package com.poutividad.jefemaestro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button; // Aunque no lo usemos activamente aún, es bueno tener la importación si el layout lo tiene.
import android.widget.ImageView; // Ídem

public class SceneTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_two); // Asegúrate que activity_scene_two.xml exista en res/layout

        // Si necesitas que el botón haga algo, obtén su referencia aquí
        // Button buttonNavigate = findViewById(R.id.button_navigate); // Asegúrate que este ID exista en activity_scene_two.xml
        // ImageView imageScene = findViewById(R.id.image_scene); // Asegúrate que este ID exista

        // Ejemplo: Configurar el botón para volver a la primera actividad
        /*
        if (buttonNavigate != null) {
            buttonNavigate.setOnClickListener(v -> {
                // Simplemente finaliza esta actividad para volver a la anterior en la pila
                finish(); 
                // O si quieres una transición de retorno específica, puedes usar:
                // supportFinishAfterTransition();
            });
        }
        */
    }
}