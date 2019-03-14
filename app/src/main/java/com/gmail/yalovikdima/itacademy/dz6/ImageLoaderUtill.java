package com.gmail.yalovikdima.itacademy.dz6;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoaderUtill {

    public static void loadImage(ImageView imageView, String url){

        Picasso.get().load(url).into(imageView);

    }

    public static void clear(ImageView imageView){
       Picasso.get().cancelRequest(imageView);

    }
}
