package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.R;

import java.util.ArrayList;

public class Dz6Activity extends Activity implements AsyncResponce {

    private ArrayList<Offer> offers;
    private MyListAdapter adapter = new MyListAdapter();
    private DownloadXml downloadXml = new DownloadXml();
    private RecyclerView recyclerView;

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
    public void processFinish(final ArrayList<Offer> offers) {
        this.offers = offers;
        adapter.setList(downloadXml.getOffers());

        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(final Offer offer, int position) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(Dz6Activity.this, recyclerView.getChildAt(position));
                //inflating menu from xml resource
                popup.inflate(R.menu.menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update:
                                Toast.makeText(Dz6Activity.this, "Update", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(Dz6Activity.this, "Delete", Toast.LENGTH_SHORT).show();
                                offers.remove(offer);
                                adapter.setList(offers );
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}


