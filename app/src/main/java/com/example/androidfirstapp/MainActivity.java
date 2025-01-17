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
            canvas.drawRGB(255, 255, 255);
            Paint brush1 = new Paint();
            brush1.setARGB(255, 0, 0, 0);
            brush1.setTextSize(80);
            Typeface face = Typeface.createFromAsset(getAssets(),
                    "Pricedown.ttf");
            brush1.setTypeface(face);
            canvas.drawText("Hello World", 0, 120, brush1);
        }
    }
}