package com.example.onlineelecstore;

public class CustomerModel {
    private String customerID;
    private String fullName;
    private String email;
    private String number;
    private String country;

    public CustomerModel(){

    }

    public CustomerModel(String customerID, String fullName, String email, String number, String country) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.number = number;
        this.country = country;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
