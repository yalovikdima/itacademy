package com.gmail.yalovikdima.itacademy.dz3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gmail.yalovikdima.itacademy.R;

public class Dz3Activity extends AppCompatActivity {

    public static Intent getIntent(Context context){
        return new Intent(context, Dz3Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3);
    }
}
