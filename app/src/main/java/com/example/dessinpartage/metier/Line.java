package com.example.dessinpartage.metier;

import android.graphics.Canvas;

public class Line extends Shape{

    private float x2, y2;

    public Line(float x, float y, float x2, float y2, int col) {
        super(x, y, col);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Canvas c) {
        c.drawLine(x, y, x2, y2, paint);
    }
}
