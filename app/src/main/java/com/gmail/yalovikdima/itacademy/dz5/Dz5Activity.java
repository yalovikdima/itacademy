package com.gmail.yalovikdima.itacademy.dz5;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gmail.yalovikdima.itacademy.R;

public class Dz5Activity extends AppCompatActivity {

    private ImageView mImageViewFilling;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz5Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);

    }
}
