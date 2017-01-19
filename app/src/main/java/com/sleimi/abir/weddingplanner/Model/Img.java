package com.sleimi.abir.weddingplanner.Model;

import android.graphics.drawable.Drawable;

public class Img {
    int id;
    String name;
    Drawable picture;
    float price;

    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */
    public Img(int id, String name, Drawable picture, float price) {
        super();
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
    }

    public Img() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Img [id=" + id + ", name=" + name + ", picture=" + picture
                + ", price=" + price + "]";
    }


}
