package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.gmail.yalovikdima.itacademy.R;

public class CreateItemActivity extends Activity implements View.OnClickListener {

    private EditText id;
    private EditText name;
    private OffersSingleton singleton = OffersSingleton.getInstance();

    public static Intent getIntent(Context context) {
        return new Intent( context, CreateItemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        id = findViewById(R.id.idEditText);
        name = findViewById(R.id.nameEditText);

        findViewById(R.id.addButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Offer offer =new Offer(id.getText().toString(), name.getText().toString());
        singleton.addOffer(offer);
        finish();
    }
}
