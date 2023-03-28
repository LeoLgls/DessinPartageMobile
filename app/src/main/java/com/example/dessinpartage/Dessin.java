package com.example.dessinpartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dessin extends AppCompatActivity {

    ZoneDessin whatIdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        whatIdraw = new ZoneDessin(this);
        //
        setContentView(R.layout.activity_dessin);

        LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.layoutPrincipal);
        layoutPrincipal.addView(whatIdraw);

    }

    //fait moi un fonction pour quitter lorque le bouton exit est cliqué
    public void exit(View view)
    {
        finish();
    }

    class ZoneDessin extends View
    {
        Paint paint = new Paint();

        public ZoneDessin(Context context)
        {
            super(context);
            setFocusable(true);

            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
        }

        public void onDraw(Canvas canvas)
        {
            canvas.drawCircle(50, 50, 30, paint);
        }
    }

}