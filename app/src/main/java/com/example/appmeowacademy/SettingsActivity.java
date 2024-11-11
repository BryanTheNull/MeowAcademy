package com.example.appmeowacademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    // Declaro una variable para la barra de navegacion
    private BottomNavigationView barraNavegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Enlazo la barra de navegacion a la variable creada
        barraNavegacion = findViewById(R.id.barraNavegacion);

        // Marcar la opción "Settings" como seleccionada
        barraNavegacion.setSelectedItemId(R.id.settings);

        // Configurar el listener para la barra de navegación
        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.settings) {
                    // Ya estamos en Settings, no hacemos nada
                    return true;
                } else if (id == R.id.home) {
                    startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.course) {
                    startActivity(new Intent(SettingsActivity.this, CourseActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.profile) {
                    startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                }
                return false;
            }
        });
    }

    public void onClickCerrarSesion(View view){
        onClickButton();
        finish();
    }

    public void onClicksendTicket(View view){
        onClickButton();

        // Obtener las referencias de los campos del formulario
        EditText nombreField = findViewById(R.id.edit_nombre);
        EditText emailField = findViewById(R.id.edit_email);
        EditText telefonoField = findViewById(R.id.edit_numero);
        EditText descriptionField = findViewById(R.id.edit_description);
        Spinner severitySpinner = findViewById(R.id.spinner_severity);
        Spinner categorySpinner = findViewById(R.id.spinner_category);

        // Validar que los campos requeridos no estén vacíos
        String nombre = nombreField.getText().toString();
        String email = emailField.getText().toString();
        String telefono = telefonoField.getText().toString();
        String description = descriptionField.getText().toString();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || description.isEmpty()) {
            // Si algún campo requerido está vacío, muestra un mensaje de advertencia
            Toast.makeText(this, "Por favor, completa todos los campos antes de enviar el ticket", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar un mensaje de confirmación al usuario
        Toast.makeText(this, "Ticket enviado con éxito. Nos pondremos en contacto contigo pronto.", Toast.LENGTH_LONG).show();

        // Limpiar los campos del formulario
        nombreField.setText("");
        emailField.setText("");
        telefonoField.setText("");
        descriptionField.setText("");
        severitySpinner.setSelection(0);
        categorySpinner.setSelection(0);
    }

    public void onClickButton(){
        // Instanciar clase de reproductor de sonido
        SoundMediaPlayer soundMediaPlayer = new SoundMediaPlayer(this);
        soundMediaPlayer.playSound();
    }

}
