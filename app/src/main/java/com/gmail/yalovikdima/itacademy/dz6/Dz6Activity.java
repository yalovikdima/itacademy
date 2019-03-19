package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.R;

import java.util.ArrayList;

public class Dz6Activity extends Activity implements AsyncResponce, View.OnClickListener {

    private OffersSingleton offersSingleton;
    private MyListAdapter adapter;
    private DownloadXml downloadXml;
    private RecyclerView recyclerView;
    private Intent intent;
    private EditText editText;
    private int pos;

    private final String RESULT ="RESULT";
    private final String POS ="POS";


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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        downloadXml.delegate = this;
        downloadXml.execute();

        findViewById(R.id.fab).setOnClickListener(this);
        intent = new Intent(this, ItemActivity.class);
        editText=findViewById(R.id.searchEditText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Offer> filterredList = new ArrayList<>();
        for(Offer offer: offersSingleton.getOffers()){
            if(offer.getName().toLowerCase().contains(text.toLowerCase())){
                filterredList.add(offer);
            }
        }
        adapter.filterList(filterredList);
    }


    @Override
    public void processFinish(final ArrayList<Offer> offers) {
        updateActivity();
    }

    @Override
    public void onClick(View v) {
        startActivity(CreateItemActivity.getIntent(this));
        adapter.notifyItemInserted(0);
    }

    private void updateActivity() {
        adapter.setList(offersSingleton.getOffers());

        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(final Offer offer, int position) {
                pos = position;
                intent.putExtra(POS, position);
                startActivityForResult(intent, 1);
        }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getStringExtra(RESULT);
        if (result.equals("DELETE")){
            adapter.notifyItemRemoved(pos);
        }else {
            adapter.notifyItemChanged(pos);
        }
    }
}


