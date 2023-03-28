package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Rectangle extends Shape{

    private float x2, y2;
    private boolean fill;

    public Rectangle(float x, float y, float x2, float y2, int col, boolean fill) {
        super(x, y, col);
        this.x2 = x2;
        this.y2 = y2;
        this.fill = fill;
        if(fill) this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        else this.paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawRect(x, y, x2, y2, paint);
    }

    @NonNull
    @Override
    public String toString() {
        return "r:"+x+":"+y+":"+x2+":"+y2+":"+col+":"+fill;
    }
}
