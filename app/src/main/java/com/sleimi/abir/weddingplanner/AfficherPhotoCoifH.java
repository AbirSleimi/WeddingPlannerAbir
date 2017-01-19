package com.sleimi.abir.weddingplanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import static com.sleimi.abir.weddingplanner.CoiffureH.listNames;
import static com.sleimi.abir.weddingplanner.CoiffureH.listPrices;

public class AfficherPhotoCoifH  extends AppCompatActivity {
    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */

    private String pos;
    TextView nomView;
    TextView priceView;
    ImageView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_photo);
        nomView= (TextView) findViewById(R.id.name);
        priceView= (TextView) findViewById(R.id.price);
        pictureView = (ImageView) findViewById(R.id.picture);
        final Drawable[] listPictures = {
                getResources().getDrawable(R.drawable.coif1),
                getResources().getDrawable(R.drawable.coif2),
                getResources().getDrawable(R.drawable.coif3),
                getResources().getDrawable(R.drawable.coif4)};

        pos= getIntent().getStringExtra("positionH");
        int  pos2 =Integer.parseInt(pos); //Character.getNumericValue(Integer.parseInt(pos));
        nomView.setText(listNames[pos2]);
        priceView.setText(listPrices[pos2] + " $");
        pictureView.setBackgroundDrawable(listPictures[pos2]);
    }
}
