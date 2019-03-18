package com.gmail.yalovikdima.itacademy.dz6;

import java.util.ArrayList;

public class OffersSingleton {
    private ArrayList<Offer> offers = new ArrayList<>();
    private DownloadXml downloadXml;

    private static final OffersSingleton instance = new OffersSingleton();

    public static OffersSingleton getInstance() {
        return instance;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void remove(Offer offer) {
        offers.remove(offer);
    }

    public Offer getOffer(int position) {
        return offers.get(position);
    }

    public void update(Offer newOffer, Offer oldOffer) {
        int i = offers.indexOf(oldOffer);
        offers.remove(oldOffer);
        offers.add(i,newOffer);
    }
}
