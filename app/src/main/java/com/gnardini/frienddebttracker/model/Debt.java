package com.gnardini.frienddebttracker.model;

import com.orm.SugarRecord;

public class Debt extends SugarRecord {

    private float amount;
    private String name;
    private boolean loan;

    public Debt() {}

    public Debt(float amount, String name, boolean loan) {
        this.amount = amount;
        this.name = name;
        this.loan = loan;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

}
