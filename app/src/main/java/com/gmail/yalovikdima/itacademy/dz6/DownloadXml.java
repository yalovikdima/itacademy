package com.gmail.yalovikdima.itacademy.dz6;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class DownloadXml extends AsyncTask<String, Void, ArrayList<Offer>> {

    public AsyncResponce delegate = null;

    private ArrayList<Offer> offers;
    private final String URL = "https://pochemuchka.by/market.xml";

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    @Override
    protected ArrayList<Offer> doInBackground(String... strings) {
        try {
            offers = new ArrayList<>();
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
        delegate.processFinish(getOffers());
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
                            } else if ("id".equalsIgnoreCase(tagName)) {
                                currentOffer.setId(textValue);
                            } else if ("name".equalsIgnoreCase(tagName)) {
                                currentOffer.setName(textValue);
                            } else if ("price".equalsIgnoreCase(tagName)) {
                                currentOffer.setPrice(textValue);
                            } else if ("picture".equalsIgnoreCase(tagName)) {
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