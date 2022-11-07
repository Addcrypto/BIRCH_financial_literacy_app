package com.example.birch.models;

public class TransactionModel {
    private String total;
    private String title;
    private String date;

    public TransactionModel(String total, String title, String date) {
        this.total = total;
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "total='" + total + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
