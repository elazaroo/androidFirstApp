package com.example.androidfirstapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebActivity extends AppCompatActivity {
    WebView web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web1=findViewById(R.id.web1);

        Bundle bundle = getIntent().getExtras();
        String url=bundle.getString("url");
        web1.loadUrl("https://" + url);
    }

    public void finish(View v) {
        finish();
    }
}