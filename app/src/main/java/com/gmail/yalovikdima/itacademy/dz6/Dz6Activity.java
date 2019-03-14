package com.gmail.yalovikdima.itacademy.dz6;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;
import android.widget.Toast;

import com.gmail.yalovikdima.itacademy.MainActivity;
import com.gmail.yalovikdima.itacademy.R;
import com.gmail.yalovikdima.itacademy.dz5.WifiService;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.yalovikdima.itacademy.dz5.WifiService.CHECK_WIFI;
import static com.gmail.yalovikdima.itacademy.dz5.WifiService.STATE;

public class Dz6Activity extends Activity {
    DownloadXml downloadXml;
    private final String URL = "https://pochemuchka.by/market.xml";

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        List<Offer> list = new ArrayList<>();
        downloadXml = (DownloadXml) new DownloadXml().execute(URL);
        list = downloadXml.getOffers();

        MyListAdapter adapter = new MyListAdapter();

        adapter.setList(list);

        adapter.setListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Offer item, int position) {
                Toast.makeText(Dz6Activity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

    }

    private class DownloadXml extends AsyncTask<String, Void, Void> {

        private ArrayList<Offer> offers;

        public ArrayList<Offer> getOffers() {
            return offers;
        }

        @Override
        protected Void doInBackground(String... Url) {
            try {
                offers = new ArrayList<>();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(new URL(Url[0])), "UTF_8");

                parse(xpp);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        public InputStream getInputStream(URL url) {
            try {
                return url.openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean parse(XmlPullParser xpp) {
            boolean status = true;
            Offer currentOffer = null;
            boolean inEntry = false;
            String textValue = "";

            try {
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {

                    String tagName = xpp.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if ("offer".equalsIgnoreCase(tagName)) {
                                inEntry = true;
                                currentOffer = new Offer(xpp.getAttributeValue(null, "id"));
                            }
                            break;
                        case XmlPullParser.TEXT:
                            textValue = xpp.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if (inEntry) {
                                if ("offer".equalsIgnoreCase(tagName)) {
                                    offers.add(currentOffer);
                                    inEntry = false;
                                } else if ("name".equalsIgnoreCase(tagName)) {
                                    currentOffer.setName(textValue);
                                } else if ("price".equalsIgnoreCase(tagName)) {
                                    currentOffer.setPrice(textValue);
                                }else  if("picture".equalsIgnoreCase(tagName)){
                                    currentOffer.setPicture(textValue);
                                }
                            }
                            break;
                        default:
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                status = false;
                e.printStackTrace();
            }
            return status;
        }
    }


}


