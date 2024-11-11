package com.example.appmeowacademy;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundMediaPlayer{
    private MediaPlayer mediaPlayer;

    public SoundMediaPlayer(Context context){
        // Iniciar el MediaPlayer con el sonido
        mediaPlayer = MediaPlayer.create(context, R.raw.soud_button);
    }

    public void playSound(){
        // Reproducir el sonido desde el principio
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    public void relese(){
        // Liberar recursos cuando no se use el reproductor
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
