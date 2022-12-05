package com.example.birch.balance;

public class Item {
    private String update_type;

    private String webhook;

    private String item_id;

    private String[] billed_products;

    private String consent_expiration_time;

    private String error;

    private String[] available_products;

    private String institution_id;

    public String getUpdate_type () {
        return update_type;
    }

    public void setUpdate_type (String update_type) {
        this.update_type = update_type;
    }

    public String getWebhook () {
        return webhook;
    }

    public void setWebhook (String webhook) {
        this.webhook = webhook;
    }

    public String getItem_id () {
        return item_id;
    }

    public void setItem_id (String item_id) {
        this.item_id = item_id;
    }

    public String[] getBilled_products () {
        return billed_products;
    }

    public void setBilled_products (String[] billed_products) {
        this.billed_products = billed_products;
    }

    public String getConsent_expiration_time () {
        return consent_expiration_time;
    }

    public void setConsent_expiration_time (String consent_expiration_time) {
        this.consent_expiration_time = consent_expiration_time;
    }

    public String getError () {
        return error;
    }

    public void setError (String error) {
        this.error = error;
    }

    public String[] getAvailable_products () {
        return available_products;
    }

    public void setAvailable_products (String[] available_products) {
        this.available_products = available_products;
    }

    public String getInstitution_id () {
        return institution_id;
    }

    public void setInstitution_id (String institution_id) {
        this.institution_id = institution_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [update_type = "+update_type+", webhook = "+webhook+", item_id = "+item_id+", billed_products = "+billed_products+", consent_expiration_time = "+consent_expiration_time+", error = "+error+", available_products = "+available_products+", institution_id = "+institution_id+"]";
    }
}
