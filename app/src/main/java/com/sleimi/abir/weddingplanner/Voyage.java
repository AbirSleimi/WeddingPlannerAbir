package com.sleimi.abir.weddingplanner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.sleimi.abir.weddingplanner.Model.Img;

import java.util.ArrayList;

/*
    Created by Abir Sleimi
    Git AbirSleimi
     */
public class Voyage extends AppCompatActivity {

    static String[] listNames = { "Espagne", "Hawai"};

    static Float[] listPrices = { (float) 2600, (float) 2400};

    ArrayList<Img> listImg;
    GridView grid;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_img);
        grid = (GridView) findViewById(R.id.grid);
        search = (EditText) findViewById(R.id.search);
        final Drawable[] listPictures = {
                getResources().getDrawable(R.drawable.voy1),
                getResources().getDrawable(R.drawable.voy2)};

        listImg = new ArrayList<Img>();

        for (int i = 0; i < listPictures.length; i++) {
            listImg.add(new Img(i + 1, listNames[i], listPictures[i],
                    listPrices[i]));
        }

        final ImgListAdapter gridadapter = new ImgListAdapter(getApplicationContext(), listImg);
        grid.setAdapter(gridadapter);
        //grid.setAdapter(new ImgListAdapter(getApplicationContext(), listImg));
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                filtrer();
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v,
                                    int position, long id) {
                //Intent i = new Intent(Intent.ACTION_VIEW);
                //i.setData(Uri.parse(gridadapter.getItem(position).toString()));//listPictures[position]);
                //listPictures[position];
                //Toast.makeText(getApplicationContext(),position+"", Toast.LENGTH_SHORT).show();
                String str = position+"";
                //Toast.makeText(getBaseContext(),position+"--------- "+gridadapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                // i.setData(Uri.parse(gridadapter.getItem(position).getImageUrlString()));
                Intent i = new Intent(Voyage.this, AfficherPhotoVoyage.class);
                i.putExtra("positionV", str);
                startActivity(i);
            }
        });

    }

    public void filtrer() {
        // retourner la chaine saisie par l'utilisateur
        String name = search.getText().toString();
        // créer une nouvelle liste qui va contenir la résultat à afficher
        ArrayList<Img> listImgNew = new ArrayList<Img>();

        for (Img Img : listImg) {
            // si le nom du Img commence par la chaine saisie , ajouter-le !
            if (Img.getName().toLowerCase().toString().startsWith(name)) {
                listImgNew.add(Img);
            }
        }
        // vider la liste
        grid.setAdapter(null);
        if (listImgNew.size() == 0) {
            listImgNew.add(new Img(100, "Pas d'élements.. réessayer !",
                    getResources().getDrawable(R.drawable.error), 0));
        }
        // ajouter la nouvelle liste
        grid.setAdapter(new ImgListAdapter(getApplicationContext(), listImgNew));
    }
}