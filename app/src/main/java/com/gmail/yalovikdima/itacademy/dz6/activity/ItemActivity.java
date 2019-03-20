package com.gmail.yalovikdima.itacademy.dz6.activity;

import android.app.Activity;
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
import com.gmail.yalovikdima.itacademy.dz6.utils.ImageLoaderUtill;
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer;
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton;

public class ItemActivity extends Activity implements View.OnClickListener {
    private Button editButton;
    private Button deleteButton;
    private Button saveButton;
    private TextView nameItem;
    private ImageView avatar;
    private EditText nameEdit;
    private Intent intent;
    private String idOffer;
    private OffersSingleton offersSingleton = OffersSingleton.getInstance();
    private Offer offer;
    private Intent resultIntent;

    private final String POS = "POS";
    private final String RESULT = "RESULT";
    private final String UPDATE = "UPDATE";
    private final String DELETE = "DELETE";
    private final String NOT_CHANGE = "NOT_CHANGE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        init();
    }

    private void init() {
        avatar = findViewById(R.id.avatar);
        nameItem = findViewById(R.id.nameItem);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        nameEdit = findViewById(R.id.nameEditText);
        saveButton = findViewById(R.id.saveButton);

        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        resultIntent = new Intent();
        resultIntent.putExtra(RESULT, NOT_CHANGE);
        setResult(RESULT_OK, resultIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        idOffer = intent.getStringExtra(POS);

        offer = offersSingleton.getOfferById(idOffer);
        if (offer != null) {
            nameItem.setText(offer.getName());

            ImageLoaderUtill.clear(avatar);
            if (!TextUtils.isEmpty(offer.getPicture())) {
                ImageLoaderUtill.loadImage(avatar, offer.getPicture());
            } else {
                avatar.setImageDrawable(null);
            }
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editButton: {
                updateClick();
                break;
            }
            case R.id.deleteButton: {
                deleteClick();
                break;
            }
            case R.id.saveButton: {
                saveClick();
                break;
            }
            default:
        }
    }

    private void updateClick() {
        nameItem.setVisibility(View.INVISIBLE);
        editButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
        nameEdit.setVisibility(View.VISIBLE);
        nameEdit.setText(nameItem.getText().toString());
        saveButton.setVisibility(View.VISIBLE);

        resultIntent.putExtra(RESULT, UPDATE);
        setResult(RESULT_OK, resultIntent);
    }

    private void deleteClick() {
        offersSingleton.removeOffer(offer);
        Toast.makeText(this, offer.getName() + " was deleted", Toast.LENGTH_SHORT).show();

        resultIntent.putExtra(RESULT, DELETE);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void saveClick() {
        Offer newOffer = new Offer(offer.getId(), nameEdit.getText().toString());
        newOffer.setPicture(offer.getPicture());
        offersSingleton.update(newOffer, offer);
        nameItem.setVisibility(View.VISIBLE);
        nameItem.setText(newOffer.getName());
        editButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.VISIBLE);
        nameEdit.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
    }
}
