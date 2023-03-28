package com.example.dessinpartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        startActivity(intentDessin);

    }
}