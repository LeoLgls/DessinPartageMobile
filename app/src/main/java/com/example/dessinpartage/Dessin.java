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

import com.example.dessinpartage.metier.Circle;
import com.example.dessinpartage.metier.Line;
import com.example.dessinpartage.metier.Rectangle;
import com.example.dessinpartage.metier.Shape;

import java.util.ArrayList;

public class Dessin extends AppCompatActivity {

    ZoneDessin whatIdraw;
    int color = Color.BLACK;
    boolean plein = false;
    boolean isDrawing = false;

    String selectedShape = "";

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


    public void selectForme(View view)
    {
        switch (view.getId())
        {
            case R.id.cercle:
                this.selectedShape = "circle";
                this.plein = false;
                break;
            case R.id.cercleplein:
                this.selectedShape =  "circle";
                this.plein = true;
                break;
            case R.id.carre:
                this.selectedShape =  "rectangle";
                this.plein = false;
                break;
            case R.id.carreplein:
                this.selectedShape =  "rectangle";
                this.plein = true;
                break;
            case R.id.ligne:
                this.selectedShape =  "line";
                break;
        }

    }

    public void setColor (View view)
    {
        switch (view.getId())
        {
            case R.id.rouge:
                color = Color.RED;
                break;
            case R.id.vert:
                color = Color.GREEN;
                break;
            case R.id.bleu:
                color = Color.BLUE;
                break;
            case R.id.jaune:
                color = Color.YELLOW;
                break;
            case R.id.noir:
                color = Color.BLACK;
                break;

        }
    }
    class ZoneDessin extends View implements View.OnTouchListener
    {

        ArrayList<Shape> alShapes;
        float x,y,x2,y2,radius;

        public ZoneDessin(Context context)
        {
            super(context);

            this.alShapes = new ArrayList<Shape>();

            setFocusable(true);

            setOnTouchListener(this);
        }

        public void onDraw(Canvas canvas)
        {
            for(Shape s : alShapes){
                s.draw(canvas);
            }

            Paint p = new Paint();
            p.setColor(color);
            p.setAntiAlias(true);
            p.setStrokeWidth(10);
            if(plein) p.setStyle(Paint.Style.FILL_AND_STROKE);
            else p.setStyle(Paint.Style.STROKE);

            if(isDrawing){
                switch (selectedShape){
                    case "line" :
                        canvas.drawLine(x, y, x2, y2, p );
                        break;

                    case "rectangle" :
                        float xhaut = Math.min(x,x2);
                        float yhaut = Math.min(y,y2);
                        float xbas  = Math.max(x,x2);
                        float ybas  = Math.max(y,y2);

                        canvas.drawRect(x,y,x2,y2,p);
                        break;

                    case "circle" :
                        float radius = Math.max(Math.abs(x - x2),Math.abs(y - y2));
                        canvas.drawCircle(x,y, radius, p);
                        break;
                }
            }
            this.invalidate();
        }


        @Override
        public boolean onTouch(View view, MotionEvent event)
        {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    this.x = event.getX();
                    this.y = event.getY();
                    isDrawing = true;
                    break;

                case MotionEvent.ACTION_MOVE:
                    this.x2 = event.getX();
                    this.y2 = event.getY();
                    this.invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    this.x2 = event.getX();
                    this.y2 = event.getY();

                    switch (selectedShape){
                        case "line" :
                            alShapes.add(new Line(x, y, x2, y2, color));
                            break;

                        case "rectangle" :
                            float xhaut = Math.min(x,x2);
                            float yhaut = Math.min(y,y2);
                            float xbas  = Math.max(x,x2);
                            float ybas  = Math.max(y,y2);

                            alShapes.add(new Rectangle(xhaut, yhaut, xbas, ybas, color, plein));
                            break;

                        case "circle" :
                            float radius = Math.max(Math.abs(x - x2),Math.abs(y - y2));

                            alShapes.add(new Circle(x, y, radius, color, plein));
                            break;
                    }
                    isDrawing = false;
                    this.invalidate();

                    break;
            }

            return true;
        }







    }

}