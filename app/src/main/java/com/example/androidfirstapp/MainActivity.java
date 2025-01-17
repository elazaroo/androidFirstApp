package com.example.androidfirstapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            ConstraintLayout layout1 =  findViewById(R.id.layout1);
            CanvasX background = new CanvasX(this);
            layout1.addView(background);
            //getSupportActionBar().hide();

            return insets;
        });
    }

    class CanvasX extends View {
        public CanvasX(Context context) {
            super(context);
        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0, 0, 255);
            int height = canvas.getHeight();

            Path path = new Path();
            path.moveTo(0, height / 2);
            path.lineTo(40, height / 2 - 30);
            path.lineTo(80, height / 2 - 60);
            path.lineTo(120, height / 2 - 90);
            path.lineTo(160, height / 2 - 120);
            path.lineTo(220, height / 2 - 150);
            path.lineTo(280, height / 2 - 180);
            path.lineTo(340, height / 2 - 210);
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 0, 0);
            pincel1.setTextSize(50);
            canvas.drawTextOnPath("Hello World Hello World", path, 0, 0,
                    pincel1);
        }
    }
}