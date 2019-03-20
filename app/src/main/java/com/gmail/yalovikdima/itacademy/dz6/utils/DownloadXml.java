package com.gmail.yalovikdima.itacademy.dz6.utils;

import android.os.AsyncTask;

import com.gmail.yalovikdima.itacademy.dz6.entity.Offer;
import com.gmail.yalovikdima.itacademy.dz6.entity.OffersSingleton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class DownloadXml extends AsyncTask<String, Void, ArrayList<Offer>> {

    public AsyncResponce delegate = null;
    private OffersSingleton singleton = OffersSingleton.getInstance();

    private final String URL = "https://pochemuchka.by/market.xml";

    private final String OFFER = "offer";
    private final String ID = "id";
    private final String NAME = "name";
    private final String PICTURE = "picture";

    @Override
    protected ArrayList<Offer> doInBackground(String... strings) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getInputStream(new URL(URL)), "UTF_8");
            parse(xpp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Offer> aVoid) {
        super.onPostExecute(aVoid);
        delegate.processFinish(singleton.getOffers());
    }

    private InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    private boolean parse(XmlPullParser xpp) {
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
                        if (OFFER.equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentOffer = new Offer(xpp.getAttributeValue(null, "id"));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if (OFFER.equalsIgnoreCase(tagName)) {
                                singleton.addOffer(currentOffer);
                                inEntry = false;
                            } else if (ID.equalsIgnoreCase(tagName)) {
                                currentOffer.setId(textValue);
                            } else if (NAME.equalsIgnoreCase(tagName)) {
                                currentOffer.setName(textValue);
                            }  else if (PICTURE.equalsIgnoreCase(tagName)) {
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