package com.example.androidfirstapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    int corX, corY, height, width;
    CanvasX background;
    Ball myBall;
    Bar myBar;
    Button resumeButton;
    TextView titleText;
    boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        corX = 100;
        corY = 100;

        ConstraintLayout cl1 = findViewById(R.id.cl1);
        background = new CanvasX(this);
        background.setId(View.generateViewId());

        myBar = new Bar(this);
        myBar.setId(View.generateViewId());

        myBall = new Ball(this, myBar);
        myBall.setId(View.generateViewId());

        myBar.setOnTouchListener(this);
        cl1.addView(background);
        cl1.addView(myBall);
        cl1.addView(myBar);

        titleText = new TextView(this);
        titleText.setText("Arkanoid");
        titleText.setTextSize(24);
        titleText.setTextColor(0xFF000000);
        titleText.setId(View.generateViewId());
        cl1.addView(titleText);

        resumeButton = new Button(this);
        resumeButton.setText("Start Game");
        resumeButton.setId(View.generateViewId());
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBall.resetGame();
                resumeButton.setVisibility(View.GONE);
                titleText.setVisibility(View.GONE);
                gameStarted = true;
            }
        });
        cl1.addView(resumeButton);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(cl1);
        constraintSet.connect(titleText.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        constraintSet.connect(titleText.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        constraintSet.connect(titleText.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        constraintSet.connect(resumeButton.getId(), ConstraintSet.TOP, titleText.getId(), ConstraintSet.BOTTOM, 32);
        constraintSet.connect(resumeButton.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(resumeButton.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        constraintSet.connect(background.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        constraintSet.connect(background.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(background.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        constraintSet.connect(myBall.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        constraintSet.connect(myBall.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(myBar.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 50);
        constraintSet.connect(myBar.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.applyTo(cl1);
    }

    public boolean onTouch(View v, MotionEvent event) {
        corX = (int) event.getX();
        myBar.setCorXBar(corX);
        return true;
    }

    class CanvasX extends View {
        public CanvasX(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            height = canvas.getHeight();
            width = canvas.getWidth();
            canvas.drawRGB(255, 255, 255);
            if (gameStarted) {
                myBall.invalidate();
            }
            myBar.invalidate();
            if (myBall.isGameOver()) {
                resumeButton.setText("Resume");
                resumeButton.setVisibility(View.VISIBLE);
                titleText.setVisibility(View.VISIBLE);
                gameStarted = false;
            }
            this.invalidate();
        }
    }
}
