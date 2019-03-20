package com.gmail.yalovikdima.itacademy.dz6.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gmail.yalovikdima.itacademy.R;
import com.gmail.yalovikdima.itacademy.dz6.utils.AsyncResponce;
import com.gmail.yalovikdima.itacademy.dz6.utils.DownloadXml;
import com.gmail.yalovikdima.itacademy.dz6.adapter.MyListAdapter;
import com.gmail.yalovikdima.itacademy.dz6.entity.Offer;
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton;

import java.util.ArrayList;

public class Dz6Activity extends Activity implements AsyncResponce, View.OnClickListener {

    private OffersSingleton offersSingleton;
    private MyListAdapter adapter;
    private DownloadXml downloadXml;
    private RecyclerView recyclerView;
    private Intent intent;
    private EditText editText;
    private int pos;
    private String textSearch;

    private final String RESULT = "RESULT";
    private final String DELETE = "DELETE";
    private final String POS = "POS";


    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);
        init();
    }

    private void init(){
        offersSingleton = OffersSingleton.getInstance();
        adapter = new MyListAdapter();

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        downloadXml = new DownloadXml();
        downloadXml.delegate = this;
        downloadXml.execute();

        intent = new Intent(this, ItemActivity.class);
        editText = findViewById(R.id.searchEditText);
        editText.addTextChangedListener(textWatcher);

        findViewById(R.id.fab).setOnClickListener(this);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            textSearch = editable.toString();
            filter(textSearch);
        }
    };

    @Override
    public void processFinish(final ArrayList<Offer> offers) {
        updateActivity();
    }

    @Override
    public void onClick(View v) {
        startActivity(AddItemActivity.getIntent(this));
        adapter.notifyItemInserted(0);
    }

    private void updateActivity() {
        adapter.setList(offersSingleton.getOffers());
        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(final Offer offer, int position) {
                pos = position;
                intent.putExtra(POS, offer.getId());
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getStringExtra(RESULT);
        if(result != null) {
            if (result.equals(DELETE)) {
                adapter.notifyItemRemoved(pos);
            } else {
                adapter.notifyItemChanged(pos);
            }
            if(textSearch!=null) {
                filter(textSearch);
            }
        }
    }

    private void filter(String text) {
        ArrayList<Offer> filterredList = new ArrayList<>();
        for (Offer offer : offersSingleton.getOffers()) {
            if (offer.getName().toLowerCase().contains(text.toLowerCase())) {
                filterredList.add(offer);
            }
        }
        adapter.filterList(filterredList);
    }
}


