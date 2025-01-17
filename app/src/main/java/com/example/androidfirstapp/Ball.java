package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Ball extends View {

    private int corXball, corYball;
    private int speedX, speedY, width, height;
    private Bar myBar;
    private boolean gameOver = false;

    public Ball(Context context, Bar myBar) {
        super(context);
        this.myBar = myBar;
        corXball = 100;
        corYball = 100;
        speedX = 5;
        speedY = 5;
    }

    protected void onDraw(Canvas canvas) {
        if (gameOver) return;

        height = canvas.getHeight();
        width = canvas.getWidth();

        if (corXball >= width - 20)
            speedX = speedX * (-1);
        if (corYball >= height - 20) {
            gameOver = true;
            return;
        }
        if (corXball <= 20)
            speedX = Math.abs(speedX);
        if (corYball <= 20)
            speedY = Math.abs(speedY);

        if (corXball >= myBar.getCorXbar() && corXball <= myBar.getCorXbar() + 100 && corYball >= myBar.getCorYbar() - 20) {
            speedY = speedY * (-1);
        }

        corXball = corXball + speedX;
        corYball = corYball + speedY;
        Paint pincel1 = new Paint();
        pincel1.setARGB(255, 255, 87, 34); // Color amigable
        pincel1.setAntiAlias(true);
        canvas.drawCircle(corXball, corYball, 20, pincel1);
    }

    public void resetGame() {
        gameOver = false;
        corXball = 100;
        corYball = 100;
        speedX = 5;
        speedY = 5;
        invalidate();
    }

    public boolean isGameOver() {
        return gameOver;
    }
}