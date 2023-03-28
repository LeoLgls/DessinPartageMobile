package metier;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Shape{

    private int x2, y2;

    public Rectangle(int x, int y, int x2, int y2, int col, boolean plein) {
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
