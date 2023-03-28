package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {

    private int radius;

    public Circle(int x, int y, int radius, int col, boolean plein) {
        super(x, y, col);
        this.radius = radius;
        if(plein) this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(x, y, radius, paint);
    }
}
