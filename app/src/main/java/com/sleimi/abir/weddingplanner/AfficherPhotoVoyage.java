package com.sleimi.abir.weddingplanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import static com.sleimi.abir.weddingplanner.Voyage.listNames;
import static com.sleimi.abir.weddingplanner.Voyage.listPrices;

public class AfficherPhotoVoyage extends AppCompatActivity {
    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */

    private String pos;
    TextView nomView;
    TextView desc;
    TextView priceView;
    ImageView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_photo);
        desc= (TextView) findViewById(R.id.ref);
        nomView= (TextView) findViewById(R.id.name);
        priceView= (TextView) findViewById(R.id.price);
        pictureView = (ImageView) findViewById(R.id.picture);
        final Drawable[] listPictures = {
                getResources().getDrawable(R.drawable.voy1),
                getResources().getDrawable(R.drawable.voy2)};
        desc.setText("Description: ");
        pos= getIntent().getStringExtra("positionV");
        int  pos2 =Integer.parseInt(pos); //Character.getNumericValue(Integer.parseInt(pos));
        nomView.setText(listNames[pos2]);
        priceView.setText(listPrices[pos2] + " $");
        pictureView.setBackgroundDrawable(listPictures[pos2]);
    }
}
