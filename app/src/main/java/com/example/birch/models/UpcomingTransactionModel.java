package com.example.birch.models;


public class UpcomingTransactionModel {
    enum Repeats {
        ONE_TIME,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    private String name;
    private float amount;
    private String dueDate;
    private Repeats repeats;

    public UpcomingTransactionModel(String name, float amount, String dueDate, Repeats repeats) {
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

    public Repeats getRepeats() { return repeats; }
    public void setRepeats(Repeats repeats) { this.repeats = repeats; }

    @Override
    public String toString() {
        return "UpcomingTransactionModel{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", dueDate='" + dueDate + '\'' +
                ", repeats=" + repeats +
                '}';
    }


}
