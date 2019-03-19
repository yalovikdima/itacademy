package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.R;

public class ItemActivity extends Activity implements View.OnClickListener {
    private Button editButton;
    private Button deleteButton;
    private Button saveButton;
    private TextView nameItem;
    private ImageView avatar;
    private EditText nameEdit;
    private Intent intent;
    private int position;
    private OffersSingleton offersSingleton = OffersSingleton.getInstance();
    private Offer offer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        avatar = findViewById(R.id.avatar);
        nameItem = findViewById(R.id.nameItem);
        editButton =findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        nameEdit = findViewById(R.id.nameEditText);
        saveButton = findViewById(R.id.saveButton);

        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        position = intent.getIntExtra("POS", 0);

        offer = offersSingleton.getOffer(position);
        nameItem.setText(offer.getName());

        ImageLoaderUtill.clear(avatar);
        if (!TextUtils.isEmpty(offer.getPicture())) {
            ImageLoaderUtill.loadImage(avatar, offer.getPicture());
        } else {
            avatar.setImageDrawable(null);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editButton: {
                nameItem.setVisibility(View.INVISIBLE);
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                nameEdit.setVisibility(View.VISIBLE);
                nameEdit.setText(nameItem.getText().toString());
                saveButton.setVisibility(View.VISIBLE);
                intent.putExtra("RESULT", "UPDATE");
                setResult(RESULT_OK, intent);
                break;
            }
            case R.id.deleteButton: {
                offersSingleton.remove(offer);
                Toast.makeText(this, offer.getName()+" was deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();

                intent.putExtra("RESULT", "DELETE");
                setResult(RESULT_OK, intent);
                finish();
                break;
            }
            case R.id.saveButton:{
                Offer newOffer = new Offer(offer.getId(), nameEdit.getText().toString());
                newOffer.setPicture(offer.getPicture());
                offersSingleton.update(newOffer, offer);
                nameItem.setVisibility(View.VISIBLE);
                nameItem.setText(newOffer.getName());
                editButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                nameEdit.setVisibility(View.INVISIBLE);
                saveButton.setVisibility(View.INVISIBLE);
                break;
            }

        }
    }
}
