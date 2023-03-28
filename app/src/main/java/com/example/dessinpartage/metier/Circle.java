package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {

    private float radius;

    public Circle(float x, float y, float radius, int col, boolean plein) {
        super(x, y, col);
        this.radius = radius;
        if(plein) this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(x, y, radius, paint);
    }
}
