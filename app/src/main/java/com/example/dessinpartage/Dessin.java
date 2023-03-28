package com.example.dessinpartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.GeomagneticField;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Dessin extends AppCompatActivity {

    ZoneDessin whatIdraw;
    Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        whatIdraw = new ZoneDessin(this);
        //on va chercher le layout activity_dessin
        setContentView(R.layout.activity_dessin);
        //on va chercher le layout principal
        LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.layoutPrincipal);
        //on ajoute le dessin au layout principal
        layoutPrincipal.addView(whatIdraw);

    }

    public void exit(View view)
    {
        finish();
    }


    public void onClick(View view)
    {
        setColor(view);
        whatIdraw.invalidate();
    }

    public void setColor (View view)
    {
        switch (view.getId())
        {
            case R.id.rouge:
                paint.setColor(Color.RED);
                break;
            case R.id.vert:
                paint.setColor(Color.GREEN);
                break;
            case R.id.bleu:
                paint.setColor(Color.BLUE);
                break;
            case R.id.jaune:
                paint.setColor(Color.YELLOW);
                break;
            case R.id.noir:
                paint.setColor(Color.BLACK);
                break;

        }
    }
    class ZoneDessin extends View implements View.OnTouchListener
    {

        float x,y,radius;

        public ZoneDessin(Context context)
        {
            super(context);
            setFocusable(true);

            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            this.setOnTouchListener(this);
        }

        public void onDraw(Canvas canvas)
        {
            canvas.drawCircle(x, y, radius, paint);
        }


        @Override
        public boolean onTouch(View view, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                x = event.getX();
                y = event.getY();
                radius = 100;
                this.invalidate();
            }

            return true;
        }







    }

}