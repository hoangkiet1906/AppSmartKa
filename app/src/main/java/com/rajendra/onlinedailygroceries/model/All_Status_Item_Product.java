package com.rajendra.onlinedailygroceries.model;

public class All_Status_Item_Product {

    private String name;
    private int imageUrl;
    private String rating;
    private String price;

    public All_Status_Item_Product(String name, int imageUrl, String rating, String price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
