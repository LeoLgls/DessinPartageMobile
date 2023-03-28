package metier;

import android.graphics.Canvas;

public class Line extends Shape{

    private int x2, y2;

    public Line(int x, int y, int x2, int y2, int col) {
        super(x, y, col);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Canvas c) {
        c.drawLine(x, y, x2, y2, paint);
    }
}
