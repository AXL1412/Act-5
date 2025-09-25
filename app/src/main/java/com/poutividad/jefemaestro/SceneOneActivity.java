package com.poutividad.jefemaestro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SceneOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_one); // Asegúrate que activity_scene_one.xml exista en res/layout

        Button buttonNavigate = findViewById(R.id.button_navigate); // Asegúrate que este ID exista en activity_scene_one.xml
        ImageView imageScene = findViewById(R.id.image_scene); // Asegúrate que este ID exista

        if (buttonNavigate != null) {
            buttonNavigate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SceneOneActivity.this, SceneTwoActivity.class);
                    
                    // Para la transición (opcional, pero lo teníamos planeado)
                    // Asegúrate que el ImageView tiene android:transitionName="scene_image_transition" en ambos layouts
                    if (imageScene != null && ViewCompat.getTransitionName(imageScene) != null) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                SceneOneActivity.this,
                                imageScene,
                                ViewCompat.getTransitionName(imageScene));
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            });
        }
    }
}