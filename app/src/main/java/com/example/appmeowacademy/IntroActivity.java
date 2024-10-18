package com.example.appmeowacademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    // Defino variables para utilizar componentes de ImageView
    private ImageView logoIntro;
    private ImageView nombreIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Referenciar los componetes con las id de las imagenes del activityIntro
        logoIntro = findViewById(R.id.logoIntro);
        nombreIntro = findViewById(R.id.nombreIntro);

        // Creo y ejecuto un Hilo(Thread)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulo una operacion de 2.5 segundos
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException err){
                    err.printStackTrace();
                }
                // Actualizo la interfaz del usuario (UI) desde el hilo principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Realizo los cambios al pasar 5 segundos
                        logoIntro.setVisibility(View.INVISIBLE);
                        nombreIntro.setVisibility(View.VISIBLE);

                        // Después de cambiar la visibilidad, cambio a la nueva actividad
                        Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // cierro la actividad actual.
                    }
                });
            }
        });
        // Iniciamos el Hilo
        thread.start();
    }
}
