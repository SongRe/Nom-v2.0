package com.example.nomv20;

import java.util.Date;

public class Vegetable {
        private String name;
        private Date expirationDate;
        private Date enteredDate;
        boolean expired;


    public Vegetable(String n, Date expDate, Date entDate) {
        name = n;
        expirationDate = expDate;
        enteredDate = entDate;
    }

    public Vegetable(String n, Date expDate, Date entDate, boolean expired) {
        name = n;
        expirationDate = expDate;
        enteredDate = entDate;
        this.expired = expired;

    }

    public Vegetable() {

    }


    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setExpired(boolean b) {
        expired = b;
    }
    public boolean getExpired() {
        return expired;
    }

    public String getExpiryString(boolean b) {
        if(b) {
            return "true";
        } else {
            return "false";
        }


    }

    public String toString() {
        return getExpiryString(expired) + "," + name + ","  + expirationDate.toString() + "," + enteredDate.toString();
    }

}
