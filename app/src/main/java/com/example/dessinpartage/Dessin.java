package com.example.dessinpartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
        //on va chercher le layout activity_dessin
        setContentView(R.layout.activity_dessin);
        //on va chercher le layout principal
        LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.layoutPrincipal);
        //on ajoute le dessin au layout principal
        layoutPrincipal.addView(whatIdraw);

    }

    //fait moi un fonction pour quitter lorque le bouton exit est cliqué
    public void exit(View view)
    {
        finish();
    }

    public void setColor (View view)
    {

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