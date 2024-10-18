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
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickGoogle(View view) {
        // Intent para abrir el navegador con la URL del curso
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.coursera.org/courseraplus/?utm_medium=sem&utm_source=gg&utm_campaign=B2C_LATAM_coursera_FTCOF_courseraplus&campaignid=20844412474&adgroupid=159999536361&device=c&keyword=coursera&matchtype=b&network=g&devicemodel=&adposition=&creativeid=683667892738&hide_mobile_promo&gad_source=1&gclid=CjwKCAjwjsi4BhB5EiwAFAL0YJHzFN03OR6k1T62YoInjTaeWqIrT04ppKcSVfsFn7wtKRLJBTcEhRoCYqwQAvD_BwE"));
        startActivity(intent);
    }
}
