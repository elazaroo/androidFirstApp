package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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

            ConstraintLayout layout1 = findViewById(R.id.layout1);
            CanvasX bakground = new CanvasX(this);
            layout1.addView(bakground);

            return insets;
        });
    }

    class CanvasX extends View {
        public CanvasX(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 255);
            int width = canvas.getWidth();
            Paint brush1 = new Paint();

            brush1.setARGB(255,255,0,0);
            canvas.drawRect(10, 10, width - 10, 40, brush1);

            brush1.setStyle(Paint.Style.STROKE);
            canvas.drawRect(10, 60, width -10, 90, brush1);

            brush1.setStrokeWidth(3);
            canvas.drawRect(10, 110, width -10, 140, brush1);
        }
    }
}