package com.example.androidfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Bar extends View {
    private int height, width, corXbar, corYbar;

    public Bar(Context context) {
        super(context);
        corXbar = 100;
    }

    public void setCorXBar(int valor) {
        corXbar = valor;
        if (valor > width - 100)
            corXbar = width - 100;
    }

    public int getCorXbar() {
        return corXbar;
    }

    public int getCorYbar() {
        corYbar = height - 130;
        return corYbar;
    }

    protected void onDraw(Canvas canvas) {
        height = canvas.getHeight();
        width = canvas.getWidth();

        Paint brush1 = new Paint();
        brush1.setARGB(255, 0, 150, 136); // Color amigable
        brush1.setAntiAlias(true);
        RectF rect = new RectF(corXbar, height - 130, corXbar + 100, height - 100);
        canvas.drawRoundRect(rect, 20, 20, brush1);
    }
}