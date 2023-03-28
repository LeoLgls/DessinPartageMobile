package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public abstract class Shape {
    protected Paint paint;
    protected float x, y;
    protected int col;

    public Shape(float x, float y, int col) {
        this.x = x;
        this.y = y;
        this.col = col;
        this.paint = new Paint();
        this.paint.setColor(col);
        this.paint.setStrokeWidth(10);
        this.paint.setAntiAlias(true);
    }

    public abstract void draw(Canvas c);
    @NonNull
    public abstract String toString();

}
