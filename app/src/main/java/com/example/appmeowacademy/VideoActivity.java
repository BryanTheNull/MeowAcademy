package com.example.appmeowacademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// Importar librerias para WebView
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Switch;
import android.widget.VideoView;


public class VideoActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String videoLink = getIntent().getStringExtra("video_link");

        // Verificar que el enlace no sea nulo
        if (videoLink != null) {
            reproducirVideo(videoLink);
        }


    }

    public void onClickCourse(View view){
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
        finish();
    }

    public void reproducirVideo(String link) {
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String videoUrl = "https://www.youtube.com/embed/" + link;
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(videoUrl);
    }
}