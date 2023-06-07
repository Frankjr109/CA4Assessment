package com.example.onlineelecstore;

public class PayPal extends PaymentModel{
    private String amount;
    private String description;
    private String URL;

    public PayPal(){

    }

    public PayPal(String amount, String description, String URL) {
        this.amount = amount;
        this.description = description;
        this.URL = URL;
    }

    public PayPal(String cardName, String cardNumber, String expiryDate, String cvc, String amount, String description, String URL) {
        super(cardName, cardNumber, expiryDate, cvc);
        this.amount = amount;
        this.description = description;
        this.URL = URL;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
