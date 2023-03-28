package com.example.dessinpartage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dessinpartage.metier.Circle;
import com.example.dessinpartage.metier.Line;
import com.example.dessinpartage.metier.Rectangle;
import com.example.dessinpartage.metier.Shape;

import java.util.LinkedList;

public class Dessin extends AppCompatActivity {

    ZoneDessin whatIdraw;
    int color = Color.BLACK;
    boolean plein = false;
    boolean isDrawing = false;
    boolean isNew;

    String selectedShape = "rectangle";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //on va chercher le layout activity_dessin
        setContentView(R.layout.activity_dessin);
        //on va chercher le layout principal
        LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.layoutPrincipal);
        //on ajoute le dessin au layout principal
        if(this.isNew) this.isNew = getIntent().getBooleanExtra("new",true);
        whatIdraw = new ZoneDessin(this);
        layoutPrincipal.addView(whatIdraw);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("turn", this.isNew);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        this.isNew = savedInstanceState.getBoolean("turn", true);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences("SAVE", MODE_PRIVATE).edit().putString("KEY",whatIdraw.getAlShape().toString()).apply();
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

    public void undo( View view){
        whatIdraw.undo();
    }

    public void deleteAll(View view){
        whatIdraw.deleteAll();
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

        LinkedList<Shape> alShapes;
        float x,y,x2,y2,radius;

        public ZoneDessin(Context context)
        {
            super(context);

            this.alShapes = new LinkedList<Shape>();

            if(!isNew){
                String list = getSharedPreferences("SAVE",MODE_PRIVATE).getString("KEY",null);
                if(list != null){
                    //suppresion des crochets
                    list = list.substring(1,list.length()-1);
                    System.out.println(list);
                    //separation des differentes formes
                    String lstShapes[] = list.split(", ");

                    for(int i=0; i<lstShapes.length; i++){
                        //isoler les valeur des formes
                        String lstValues[] = lstShapes[i].split(":");

                        switch (lstValues[0]){
                            case "c":
                                this.alShapes.add(new Circle(
                                        Float.parseFloat(lstValues[1]),
                                        Float.parseFloat(lstValues[2]),
                                        Float.parseFloat(lstValues[3]),
                                        Integer.parseInt(lstValues[4]),
                                        Boolean.parseBoolean(lstValues[5])
                                ));
                                break;

                            case "r":
                                this.alShapes.add(new Rectangle(
                                        Float.parseFloat(lstValues[1]),
                                        Float.parseFloat(lstValues[2]),
                                        Float.parseFloat(lstValues[3]),
                                        Float.parseFloat(lstValues[4]),
                                        Integer.parseInt(lstValues[5]),
                                        Boolean.parseBoolean(lstValues[6])
                                ));
                                break;

                            case "l":
                                this.alShapes.add(new Line(
                                        Float.parseFloat(lstValues[1]),
                                        Float.parseFloat(lstValues[2]),
                                        Float.parseFloat(lstValues[3]),
                                        Float.parseFloat(lstValues[4]),
                                        Integer.parseInt(lstValues[5])
                                ));
                                break;
                        }
                    }
                }
            }

            setFocusable(true);

            setOnTouchListener(this);
        }

        public void undo(){
            if(this.alShapes.size()>0){
                this.alShapes.removeLast();
                this.invalidate();
            }
        }

        public void deleteAll(){
            this.alShapes.clear();
        }

        public LinkedList<Shape> getAlShape(){
            return alShapes;
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
                    this.x2 = event.getX();
                    this.y2 = event.getY();
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