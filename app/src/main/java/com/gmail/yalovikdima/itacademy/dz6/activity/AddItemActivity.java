package com.gmail.yalovikdima.itacademy.dz6.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gmail.yalovikdima.itacademy.R;
import com.gmail.yalovikdima.itacademy.dz6.utils.ImageLoaderUtill;
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer;
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton;

import java.util.Random;

public class AddItemActivity extends Activity implements View.OnClickListener {

    private EditText name;
    private OffersSingleton singleton = OffersSingleton.getInstance();
    private final Random random = new Random();
    private final String Url = "https://picsum.photos/300/300/?random";
    private ImageView avatar;

    public static Intent getIntent(Context context) {
        return new Intent( context, AddItemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        name = findViewById(R.id.nameEditText);
        findViewById(R.id.addButton).setOnClickListener(this);
        avatar = findViewById(R.id.avatarCreate);
        ImageLoaderUtill.loadImage(avatar, Url);
    }

    @Override
    public void onClick(View v) {
        Offer offer =new Offer(String.valueOf(random.nextInt()), name.getText().toString());
        offer.setPicture(Url);
        singleton.addOffer(offer);
        finish();
    }
}
