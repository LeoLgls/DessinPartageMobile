package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Circle extends Shape {

    private float radius;
    private boolean fill;

    public Circle(float x, float y, float radius, int col, boolean fill) {
        super(x, y, col);
        this.radius = radius;
        this.fill = fill;
        if(fill) this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        else this.paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(x, y, radius, paint);
    }

    @NonNull
    @Override
    public String toString() {
        return "c:"+x+":"+y+":"+radius+":"+col+":"+fill;
    }
}
