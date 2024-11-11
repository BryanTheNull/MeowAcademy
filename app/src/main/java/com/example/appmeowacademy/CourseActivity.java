package com.example.appmeowacademy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CourseActivity extends AppCompatActivity {

    // Declaro una variable para la barra de navegacion
    private BottomNavigationView barraNavegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Enlazo la barra de navegacion a la variable creada
        barraNavegacion = findViewById(R.id.barraNavegacion);

        // Marcar la opción "Course" como seleccionada
        barraNavegacion.setSelectedItemId(R.id.course);

        // Configurar el listener para la barra de navegación
        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.course) {
                    // Ya estamos en Course, no hacemos nada
                    return true;
                } else if (id == R.id.home) {
                    startActivity(new Intent(CourseActivity.this, HomeActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.profile) {
                    startActivity(new Intent(CourseActivity.this, ProfileActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.settings) {
                    startActivity(new Intent(CourseActivity.this, SettingsActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                }
                return false;
            }
        });
    }

    public void onClickHome(View view){
        // Reproducir sonido al presioanr el boton
        onClickButton();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickReproducirCurso(View view) {
        // Reproducir sonido al presioanr el boton
        onClickButton();

        // Intent para abrir el navegador con la URL del curso
        Intent intent = new Intent(this, VideoActivity.class);

        // Verificar cuál contenedor fue presionado y establecer el enlace adecuado
        if(view.getId() == R.id.cursoAlgoritmos){
            intent.putExtra("video_link", "sQLn2asTefo");
        } else if (view.getId() == R.id.cursoAndroid) {
            intent.putExtra("video_link", "H8tykt3pKTU");
        } else if (view.getId() == R.id.cursoCloud) {
            intent.putExtra("video_link", "h4Af5bbFAq0");
        } else if (view.getId() == R.id.cursoHacking) {
            intent.putExtra("video_link", "spMYZHepjko");
        }  else if (view.getId() == R.id.cursoGame) {
            intent.putExtra("video_link", "3TnG0lbHEco");
        } else if (view.getId() == R.id.cursoLinux) {
            intent.putExtra("video_link", "L906Kti3gzE");
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
