package com.sleimi.abir.weddingplanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import static com.sleimi.abir.weddingplanner.Robes.listNamesR;
import static com.sleimi.abir.weddingplanner.Robes.listPricesR;

public class AfficherPhotoRobe extends AppCompatActivity {
    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */

    private String posR;
    TextView nomView;
    TextView priceView;
    ImageView pictureView;/*
    String[] listNames = { "1", "2", "3", "Cake", "Cheese", "Chicken", "Cocktail", "Coffe",
            "Coffe black", "Humberger", "IceCream", "Pizza", "Sandwich",
            "Soupe", "Yaghurt" };

    Float[] listPrices = { (float) 1000, (float) 1400, (float) 1300,(float) 1.5, (float) 1.4, (float) 4.3, (float) 1.8,
            (float) 0.7, (float) 0.650, (float) 1.7, (float) 1.200,
            (float) 4.500, (float) 2.500, (float) 2.8, (float) 0.500 };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_photo);
        nomView= (TextView) findViewById(R.id.name);
        priceView= (TextView) findViewById(R.id.price);
        pictureView = (ImageView) findViewById(R.id.picture);
        final Drawable[] listPictures = {
                getResources().getDrawable(R.drawable.robe1),
                getResources().getDrawable(R.drawable.robe2),
                getResources().getDrawable(R.drawable.robe3),
                getResources().getDrawable(R.drawable.robe4),
                getResources().getDrawable(R.drawable.robe5),
                getResources().getDrawable(R.drawable.robe6),
                getResources().getDrawable(R.drawable.robe7),
                getResources().getDrawable(R.drawable.robe8)};

        posR= getIntent().getStringExtra("positionR");
        int  pos3 =Integer.parseInt(posR); //Character.getNumericValue(Integer.parseInt(pos));
        nomView.setText(listNamesR[pos3]);
        priceView.setText(listPricesR[pos3] + " $");
        pictureView.setBackgroundDrawable(listPictures[pos3]);
    }
}
