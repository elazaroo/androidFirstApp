package com.example.androidfirstapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
            Paint brush1 = new Paint();
            brush1.setARGB(255, 255, 0, 0);
            brush1.setTextSize(30);
            brush1.setTypeface(Typeface.SERIF);
            canvas.drawText("Hello World (SERIF)", 0, 70, brush1);
            brush1.setTypeface(Typeface.SANS_SERIF);
            canvas.drawText("Hello World (SANS SERIF)", 0, 100, brush1);
            brush1.setTypeface(Typeface.MONOSPACE);
            canvas.drawText("Hello World (MONOSPACE)", 0, 140, brush1);
            Typeface tf = Typeface.create(Typeface.SERIF, Typeface.ITALIC);
            brush1.setTypeface(tf);
            canvas.drawText("Hello World (SERIF ITALIC)", 0, 180, brush1);
            tf = Typeface.create(Typeface.SERIF, Typeface.ITALIC | Typeface.BOLD);
            brush1.setTypeface(tf);
            canvas.drawText("Hello World (SERIF ITALIC BOLD)", 0, 220, brush1);
        }
    }
}