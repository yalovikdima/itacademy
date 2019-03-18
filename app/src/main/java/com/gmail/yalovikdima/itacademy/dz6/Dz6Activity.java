package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.R;

import java.util.ArrayList;

public class Dz6Activity extends Activity implements AsyncResponce, View.OnClickListener {

    OffersSingleton offersSingleton;
    private MyListAdapter adapter;
    private DownloadXml downloadXml;
    private RecyclerView recyclerView;
    private Intent intent;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        offersSingleton = OffersSingleton.getInstance();
        adapter = new MyListAdapter();
        downloadXml = new DownloadXml();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        downloadXml.delegate = this;
        downloadXml.execute();

        findViewById(R.id.fab).setOnClickListener(this);
        intent = new Intent(this, ItemActivity.class);

    }


    @Override
    public void processFinish(final ArrayList<Offer> offers) {
        updateActivity();

    }

    @Override
    public void onClick(View v) {
        startActivity(CreateItemActivity.getIntent(this));
        updateActivity();
    }

    private void updateActivity() {
        adapter.setList(offersSingleton.getOffers());

        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(final Offer offer, int position) {

                intent.putExtra("POS", position);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateActivity();
    }
}


