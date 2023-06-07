package com.example.onlineelecstore;

public class Invoice extends PaymentModel{
    private String InvoiceNumber;
    private String description;

    public Invoice(){

    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
