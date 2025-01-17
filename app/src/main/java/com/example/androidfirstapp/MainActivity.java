package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0, 0, 255);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.image1);
            canvas.drawBitmap(bmp,  0,  0, null);
        }
    }
}