package com.team1.ecommerce.shared.model;

import java.util.Date;

/**
 * Represents a completed purchase.
 */
public class Transaction {

    private String id;
    private String userId;
    private Date date;
    private double amount;

    public Transaction() {
    }

    public Transaction(String id, String userId, Date date, double amount) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

