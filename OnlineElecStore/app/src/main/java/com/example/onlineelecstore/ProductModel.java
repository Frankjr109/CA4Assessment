package com.example.onlineelecstore;

public class ProductModel {
    private String productID;
    private String title;
    private String manufacturer;
    private String price;
    private String category;

    public ProductModel(){

    }

    public ProductModel(String productID, String title, String manufacturer, String price, String category) {
        this.productID = productID;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
