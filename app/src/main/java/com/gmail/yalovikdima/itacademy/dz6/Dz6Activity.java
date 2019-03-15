package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.R;

import java.util.ArrayList;

public class Dz6Activity extends Activity implements AsyncResponce {

    private ArrayList<Offer> offers;
    private MyListAdapter adapter = new MyListAdapter();
    private DownloadXml downloadXml = new DownloadXml();
    private RecyclerView recyclerView ;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        downloadXml.delegate = this;
        downloadXml.execute();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dz6Activity.this, "ADD", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void processFinish(ArrayList<Offer> offers) {
        this.offers = offers;
        adapter.setList(downloadXml.getOffers());

        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Offer item, int position) {
                Toast.makeText(Dz6Activity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}


