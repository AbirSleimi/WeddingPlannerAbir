package com.sleimi.abir.weddingplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleimi.abir.weddingplanner.Model.Img;

import java.util.List;

/*
    Created by Abir Sleimi
    Git AbirSleimi
     */
public class ImgListAdapter extends BaseAdapter {

    private List<Img> listImg = null;
    LayoutInflater layoutInflater;
    Context context;
    private int lastPosition = -1;

    // constructeur
    public ImgListAdapter(Context context, List<Img> listImg) {
        this.listImg = listImg;
        layoutInflater = LayoutInflater.from(context);
        this.listImg = listImg;
        this.context = context;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listImg.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listImg.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    static class ViewHolder {
        TextView nomView;
        TextView priceView;
        ImageView pictureView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            // initialisation des vues
            holder.nomView = (TextView) convertView.findViewById(R.id.name);
            holder.priceView = (TextView) convertView.findViewById(R.id.price);
            holder.pictureView = (ImageView) convertView
                    .findViewById(R.id.picture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // affchier les données convenablement dans leurs positions
        holder.nomView.setText(listImg.get(position).getName());
        holder.priceView.setText(String.valueOf(listImg.get(position)
                .getPrice()) + " $");
        holder.pictureView.setBackgroundDrawable(listImg.get(position)
                .getPicture());

        // changer R.anim.ton_effet
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.down_from_top
                        : R.anim.up_from_bottom);

        convertView.startAnimation(animation);
        lastPosition = position;
        return convertView;

    }
}