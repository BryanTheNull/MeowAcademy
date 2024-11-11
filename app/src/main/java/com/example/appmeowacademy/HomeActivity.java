package com.example.appmeowacademy;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import  android.view.View;


public class HomeActivity extends AppCompatActivity {

    // Declaro una variable para la barra de navegacion
    private BottomNavigationView barraNavegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        // Enlazo la barra de navegacion a la variable creada
        barraNavegacion = findViewById(R.id.barraNavegacion);

        // Marcar la opción "Home" como seleccionada
        barraNavegacion.setSelectedItemId(R.id.home);

        // Configurar el listener para la barra de navegación
        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                // Ya estamos en Home, no hacemos nada
                if (id == R.id.home) {
                    return true;
                } else if (id == R.id.course) {
                    startActivity(new Intent(HomeActivity.this, CourseActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.profile) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.settings) {
                    startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                }
                return false;
            }
        });
    }

    public void onClickCours(View view){
        // Reproducir sonido al presionar el boton
        onClickButton();

        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickReproducirCurso(View view) {
        // Reproducir sonido al presioanr el boton
        onClickButton();

        // Intent para abrir el navegador con la URL del curso
        Intent intent = new Intent(this, VideoActivity.class);

        // Verificar cuál contenedor fue presionado y establecer el enlace adecuado
        if(view.getId() == R.id.cursoJava){
            intent.putExtra("video_link", "W86KTBSiX2o");
        } else if (view.getId() == R.id.cursoPython) {
            intent.putExtra("video_link", "nKPbfIU442g");
        } else if (view.getId() == R.id.cursoMySql) {
            intent.putExtra("video_link", "DFg1V-rO6Pg");
        }
        startActivity(intent);
        finish();
    }

    public void onClickButton(){
        // Instanciar clase de reproductor de sonido
        SoundMediaPlayer soundMediaPlayer = new SoundMediaPlayer(this);
        soundMediaPlayer.playSound();
    }



}
