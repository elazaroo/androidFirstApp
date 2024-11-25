package com.example.androidfirstapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    Button bt;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            bt = findViewById(R.id.btNoLoop);

            return insets;
        });
    }

    public void destroy() {
        if (mp != null)
            mp.release();
    }

    public void start(View v) {
        destroy();
        mp = MediaPlayer.create(this, R.raw.luv);
        mp.start();
        String op = bt.getText().toString();
        if (op.equals("no reproducir en forma circular"))
            mp.setLooping(false);
        else
            mp.setLooping(true);
    }

    public void pause(View v) {
        if (mp != null && mp.isPlaying()) {
            pos = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void continueC(View v) {
        if (mp != null && mp.isPlaying() == false) {
            mp.seekTo(pos);
            mp.start();
        }
    }

    public void stop(View v) {
        if (mp != null) {
            mp.stop();
            pos = 0;
        }
    }

    public void circle(View v) {
        stop(null);
        String op = bt.getText().toString();
        if (op.equalsIgnoreCase("no reproducir en forma circular"))
            bt.setText("reproducir en forma circular");
        else
            bt.setText("no reproducir en forma circular");
    }
}