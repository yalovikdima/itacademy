package com.gmail.yalovikdima.itacademy.dz3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gmail.yalovikdima.itacademy.R;
import com.gmail.yalovikdima.itacademy.util.CircularTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Dz3Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText link;
    private ProgressBar progressBar;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz3Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3);
        init();
    }

    private void init(){
        imageView = findViewById(R.id.imageView);
        Button buttonLoad = findViewById(R.id.buttonLoad);
        link = findViewById(R.id.link);
        progressBar = findViewById(R.id.progressBar);
        buttonLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(link.getText().toString()).transform(new CircularTransformation()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageResource(R.drawable.ic_error_black);
            }
        });
    }


}
