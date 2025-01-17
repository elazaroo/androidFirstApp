package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private int corx, cory;
    private CanvasX background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            corx = 100;
            cory = 100;
            ConstraintLayout layout1 = findViewById(R.id.layout1);
            background = new CanvasX(this);
            background.setOnTouchListener(this);
            layout1.addView(background);

            return insets;
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        corx = (int) event.getX();
        cory = (int) event.getY();
        background.invalidate();
        return true;
    }

    class CanvasX extends View {

        public CanvasX(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 0);
            Paint brush1 = new Paint();
            brush1.setARGB(255, 255, 0, 0);
            brush1.setStrokeWidth(4);
            brush1.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(corx, cory, 20, brush1);
        }
    }
}