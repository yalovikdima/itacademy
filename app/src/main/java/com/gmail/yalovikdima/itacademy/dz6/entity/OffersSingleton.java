package com.gmail.yalovikdima.itacademy.dz6.entity;

import java.util.ArrayList;

public class OffersSingleton {
    private ArrayList<Offer> offers = new ArrayList<>();

    private static final OffersSingleton instance = new OffersSingleton();

    public static OffersSingleton getInstance() {
        return instance;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer) {
        offers.add(0, offer);
    }

    public void removeOffer(Offer offer) {
        offers.remove(offer);
    }

    public Offer getOffer(int position) {
        return offers.get(position);
    }

    public void update(Offer newOffer, Offer oldOffer) {
        int i = offers.indexOf(oldOffer);
        offers.remove(oldOffer);
        offers.add(i, newOffer);
    }

    public Offer getOfferById(String id) {
        for (Offer offer : offers) {
            if (offer.getId().equals(id)) {
                return offer;
            }
        }
        return null;
    }
}

