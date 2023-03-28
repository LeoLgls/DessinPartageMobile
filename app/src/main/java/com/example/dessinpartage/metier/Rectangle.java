package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Shape{

    private float x2, y2;

    public Rectangle(float x, float y, float x2, float y2, int col, boolean plein) {
        super(x, y, col);
        this.x2 = x2;
        this.y2 = y2;
        if(plein) this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawRect(x, y, x2, y2, paint);
    }
}
