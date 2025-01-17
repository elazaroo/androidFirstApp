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

    private Circle circle1, circle2;
    private CanvasX background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            circle1 = new Circle(100, 100, 50, 255, 0, 0); // Red circle
            circle2 = new Circle(200, 200, 50, 0, 0, 255); // Blue circle
            ConstraintLayout layout1 = findViewById(R.id.layout1);
            background = new CanvasX(this);
            background.setOnTouchListener(this);
            layout1.addView(background);

            return insets;
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (circle1.contains(x, y)) {
            circle1.setPosition(x, y);
        } else if (circle2.contains(x, y)) {
            circle2.setPosition(x, y);
        }

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
            brush1.setStrokeWidth(10); // Thicker border
            brush1.setStyle(Paint.Style.STROKE);

            brush1.setARGB(255, circle1.getRed(), circle1.getGreen(), circle1.getBlue());
            canvas.drawCircle(circle1.getX(), circle1.getY(), circle1.getRadius(), brush1);

            brush1.setARGB(255, circle2.getRed(), circle2.getGreen(), circle2.getBlue());
            canvas.drawCircle(circle2.getX(), circle2.getY(), circle2.getRadius(), brush1);

        }
    }

    class Circle {
    private int x, y, radius;
    private int red, green, blue;

    public Circle(int x, int y, int radius, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(int x, int y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
}