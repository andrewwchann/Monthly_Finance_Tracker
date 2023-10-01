package com.example.financetracker;

public class Data {
    private String date;
    private String expenseName;
    private String expenseCost;
    private String comment;

    Data(String date, String expenseName, String expenseCost, String comment) {
        this.date = date;
        this.expenseName = expenseName;
        this.expenseCost = expenseCost;
        this.comment = comment;
    }

    String getDate() {
        return this.date;
    }

    String getExpenseName() {
        return this.expenseName;
    }

    String getExpenseCost() {
        return this.expenseCost;
    }

    String getComment() {
        return this.comment;
    }

    void setDate(String date) {
        this.date = date;
    }
    void setExpenseName(String name) {
        this.expenseName = name;
    }
    void setExpenseCost(String cost) {
        this.expenseCost = cost;
    }
    void setComment(String comment) {
        this.comment = comment;
    }

}
