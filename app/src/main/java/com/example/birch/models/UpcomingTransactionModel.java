package com.example.birch.models;


public class UpcomingTransactionModel {
//    public enum Repeats {
//        ONE_TIME,
//        DAILY,
//        WEEKLY,
//        MONTHLY,
//        YEARLY
//    }


    // TODO: private Boolean isPayed;
    private String ownerEmail;
    private String name;
    private float amount;
    private String dueDate;
    private String repeats;

    public UpcomingTransactionModel () {}

    public UpcomingTransactionModel(String ownerEmail, String name, float amount, String dueDate, String repeats) {
        this.ownerEmail = ownerEmail;
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.repeats = repeats;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getRepeats() { return repeats; }
    public void setRepeats(String repeats) { this.repeats = repeats; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    @Override
    public String toString() {
        return "UpcomingTransactionModel{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", dueDate='" + dueDate + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", repeats=" + repeats +
                '}';
    }


}
