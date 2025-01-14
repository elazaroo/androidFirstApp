package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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


            ConstraintLayout layout1 =findViewById(R.id.layout1);
            CanvasX background = new CanvasX(this);
            layout1.addView(background);
            return insets;
        });
    }

    class CanvasX extends View {
        public CanvasX(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();

            //Green font
            canvas.drawRGB(141, 183, 57);

            Paint brush1 = new Paint();

            // White brush
            brush1.setARGB(255, 255, 255, 255);

            // Main rectangle
            brush1.setStyle(Paint.Style.STROKE);
            brush1.setStrokeWidth(8);
            canvas.drawRect(30, 30, width - 30, height - 30, brush1);


            // Bottom area
            canvas.drawRect(250, height - 30, width - 250, height - 350, brush1);
            // Small bottom area
            canvas.drawRect(450, height - 30, width - 450, height - 150, brush1);
            // Bottom soccer goal
            canvas.drawRect(600, height - 30, width - 600, height - 20, brush1);
            // Bottom penalty dot
            brush1.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, height - 250, 10, brush1);
            brush1.setStyle(Paint.Style.STROKE);

            // Top area
            canvas.drawRect(250, 30, width - 250, 350, brush1);
            // Small top area
            canvas.drawRect(450, 30, width - 450, 150, brush1);
            // Top soccer goal
            canvas.drawRect(600, 30, width - 600, 20, brush1);
            // Top penalty dot
            brush1.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, 250, 10, brush1);
            brush1.setStyle(Paint.Style.STROKE);

            // Middle line
            brush1.setStrokeWidth(4);
            canvas.drawLine(30, height / 2, width - 30, height / 2, brush1);

            // Middle circle
            canvas.drawCircle(width / 2, height / 2, 100, brush1);

            // Middle point
            brush1.setStrokeWidth(15);
            canvas.drawPoint(width / 2, height / 2, brush1);
        }
    }
}