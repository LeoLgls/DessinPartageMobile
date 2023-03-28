package com.example.dessinpartage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //fait moi un fonction pour quitter lorque le bouton exit est cliqué
    public void exit(View view)
    {
        finish();
    }


    //méthode pour aller à l'activité ouvrirDessin
    public void ouvrirDessin(View view)
    {
        //on va à l'activité dessin
        Intent intentDessin = new Intent(this, Dessin.class);

        intentDessin.putExtra("new",view.getId()==R.id.newDraw);
        startActivity(intentDessin);

    }
}