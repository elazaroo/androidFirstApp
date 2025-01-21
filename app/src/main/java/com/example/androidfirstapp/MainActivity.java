package com.example.androidfirstapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivBeetle;
    private AnimationDrawable myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ivBeetle=findViewById(R.id.ivBeetle);
        ivBeetle.setBackgroundResource(R.drawable.anim);
        myAnim=(AnimationDrawable) ivBeetle.getBackground();

        ivBeetle.animate().x(400).y(1600).setDuration(0);
        ivBeetle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnim.start();
                run(5000);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void run(int duracion){

        ivBeetle.animate().x(200).y(10).setDuration(duracion);

        ivBeetle.postDelayed(new Runnable() {
            @Override
            public void run() {
                myAnim.stop();
                ivBeetle.setBackgroundResource(R.drawable.beetle10);
            }
        },duracion);
    }
}