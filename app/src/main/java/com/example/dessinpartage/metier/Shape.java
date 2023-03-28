package com.example.dessinpartage.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Shape {
    protected Paint paint;
    protected int x, y;

    public Shape(int x, int y, int col) {
        this.x = x;
        this.y = y;
        this.paint = new Paint();
        this.paint.setColor(col);
        this.paint.setAntiAlias(true);
    }

    public abstract void draw(Canvas c);

}
