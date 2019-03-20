package com.gmail.yalovikdima.itacademy.dz6.entity;

public class Offer {

    private String id;
    private String price;
    private String currencyId;
    private String categiryId;
    private String picture;
    private Boolean delivery;
    private String name;
    private String vendor;
    private String description;

    public Offer(String id) {
        this.id = id;
    }

    public Offer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Offer(String id, String price, String currencyId, String categiryId, String picture, Boolean delivery, String name, String vendor, String description) {
        this.id = id;
        this.price = price;
        this.currencyId = currencyId;
        this.categiryId = categiryId;
        this.picture = picture;
        this.delivery = delivery;
        this.name = name;
        this.vendor = vendor;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCategiryId() {
        return categiryId;
    }

    public void setCategiryId(String categiryId) {
        this.categiryId = categiryId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", currencyId='" + currencyId + '\'' +
                ", categiryId='" + categiryId + '\'' +
                ", picture='" + picture + '\'' +
                ", delivery=" + delivery +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
