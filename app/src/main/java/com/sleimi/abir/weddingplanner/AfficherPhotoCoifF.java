package com.sleimi.abir.weddingplanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import static com.sleimi.abir.weddingplanner.CoiffureF.listNames;
import static com.sleimi.abir.weddingplanner.CoiffureF.listPrices;


public class AfficherPhotoCoifF  extends AppCompatActivity {
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
                getResources().getDrawable(R.drawable.coiff1),
                getResources().getDrawable(R.drawable.coiff2),
                getResources().getDrawable(R.drawable.coiff3),
                getResources().getDrawable(R.drawable.coiff4),
                getResources().getDrawable(R.drawable.coiff5),
                getResources().getDrawable(R.drawable.coiff6)};
        pos= getIntent().getStringExtra("positionF");
        int  pos2 =Integer.parseInt(pos); //Character.getNumericValue(Integer.parseInt(pos));
        nomView.setText(listNames[pos2]);
        priceView.setText(listPrices[pos2] + " $");
        pictureView.setBackgroundDrawable(listPictures[pos2]);
    }
}
